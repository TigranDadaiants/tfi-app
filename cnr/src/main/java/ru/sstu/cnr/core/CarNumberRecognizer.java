package ru.sstu.cnr.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.sstu.cnr.patterns.Digit;
import ru.sstu.cnr.patterns.Latin;
import ru.sstu.images.analysis.Distribution;
import ru.sstu.images.analysis.Image;
import ru.sstu.images.analysis.Peak;
import ru.sstu.images.analysis.Projection;
import ru.sstu.images.filters.BlackAndWhiteFilter;
import ru.sstu.images.filters.BlurFilter;
import ru.sstu.images.filters.InverseFilter;
import ru.sstu.images.filters.edges.SobelFilter;
import ru.sstu.ocr.core.CharacterPattern;
import ru.sstu.ocr.core.CharacterRecognizer;
import ru.sstu.ocr.core.CharacterSample;
import ru.sstu.ocr.core.CharacterType;

/**
 * <code>CarNumberRecognizer</code> class recognizes car numbers on images.
 *
 * @author Denis_Murashev
 * @since CNR 1.0
 */
public class CarNumberRecognizer {

	private static final List<CharacterPattern> PATTERNS
			= new ArrayList<CharacterPattern>();
	static {
		PATTERNS.addAll(Arrays.asList(Digit.values()));
		PATTERNS.addAll(Arrays.asList(Latin.values()));
	}

	private List<RecognizerListener> listeners
			= new ArrayList<RecognizerListener>();

	private final RecognizerSettings settings;

	/**
	 * Initializes recognizer using given settings.
	 *
	 * @param settings recognizer settings
	 */
	public CarNumberRecognizer(RecognizerSettings settings) {
		this.settings = settings;
	}

	/**
	 * Adds given listener.
	 *
	 * @param listener listener
	 */
	public void addListener(RecognizerListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove given listener.
	 *
	 * @param listener listener
	 */
	public void removeListener(RecognizerListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Recognizes car number on given image.
	 *
	 * @param image source image
	 * @return car number string
	 */
	public String recognize(Image image) {
		for (RecognizerListener l : listeners) {
			l.started(image);
		}
		image.normalize();
		for (RecognizerListener l : listeners) {
			l.grayed(image);
		}
		Image band = extractBand(image);
		for (RecognizerListener l : listeners) {
			l.horizontalBandSelected(band);
		}
		List<Image> roughImages = extractCharacterCandidates(band);
		for (RecognizerListener l : listeners) {
			l.symbolImagesSelected(roughImages);
		}
		List<CharacterPattern> patterns = extractPatterns(roughImages);
		StringBuilder builder = new StringBuilder();
		for (CharacterPattern p : patterns) {
			builder.append(p.getCharacter());
		}
		return builder.toString();
	}

	private Image extractBand(Image image) {
		Image verticalEdges = new SobelFilter().vertical().filter(image);
		for (RecognizerListener l : listeners) {
			l.verticalEdgesSelected(verticalEdges);
		}
		Projection vertical = Projection.vertical(verticalEdges);
		List<Peak> peaks = vertical.findPeaks(settings.getBandPeakFoot(),
				settings.getMinBandHeight());
		for (RecognizerListener l : listeners) {
			l.verticalProjection(vertical, peaks);
		}
		if (peaks.isEmpty()) {
			// No peaks found. Actually shouldn't get here.
			throw new RuntimeException("Cannot select band at the image");
		}
		Peak peak = peaks.get(0);
		return image.getRegion(0, peak.getLeft(),
				image.getWidth(), peak.getRight());
	}

	private List<Image> extractCharacterCandidates(Image band) {
		List<Peak> peaks = findCharacterPeaks(band);
		List<Image> roughImages = new ArrayList<Image>();
		float threshold = settings.getSymbolDispersionThreshold();
		double valueThreshold = 0;
		double areaThreshold = 0;
		for (Peak p : peaks) {
			valueThreshold += p.getValue();
			areaThreshold += p.getArea();
		}
		valueThreshold /= peaks.size();
		areaThreshold /= peaks.size();
		valueThreshold *= settings.getSymbolPeakValueThreshold();
		areaThreshold *= settings.getSymbolPeakAreaThreshold();
		for (Peak p : peaks) {
			Image item = band.getRegion(p.getLeft(), 0,
					p.getRight(), band.getHeight());
			Distribution distribution = new Distribution(item);
			if (distribution.getDispersion() > threshold
					&& p.getValue() > valueThreshold
					&& p.getArea() > areaThreshold) {
				roughImages.add(item);
			}
		}
		return roughImages;
	}

	private List<Peak> findCharacterPeaks(Image band) {
		float invertColorThreshold = settings.getInvertColorThreshold();
		Distribution distribution = new Distribution(band);
		if (distribution.getAverage() < invertColorThreshold) {
			band.applyFilter(new InverseFilter());
		}
		Image horizontalEdges = new SobelFilter().horizontal().filter(band);
		for (RecognizerListener l : listeners) {
			l.horizontalEdgesSelected(horizontalEdges);
		}
		Projection horizontal = Projection.horizontal(horizontalEdges);
		float peakFoot = settings.getRoughSymbolPeakFoot();
		int symbolWidth = settings.getMinSymbolWidth();
		List<Peak> peaks = horizontal.findPeaks(peakFoot, symbolWidth);
		if (peaks.isEmpty()) {
			// No peaks found. No symbols can be recognized.
			throw new RuntimeException("Cannot select symbols at the image");
		}
		Collections.sort(peaks, Peak.APPEARANCE_COMPARATOR);
		for (RecognizerListener l : listeners) {
			l.horizontalProjection(horizontal, peaks);
		}
		return peaks;
	}

	private List<CharacterPattern> extractPatterns(List<Image> roughImages) {
		List<CharacterSample> samples = new ArrayList<CharacterSample>();
		for (Image image : roughImages) {
			image.normalize();
			image.applyFilter(new InverseFilter());
			image.applyFilter(new BlurFilter());
			float blackWhiteThreshold = settings.getBlackWhiteThreshold();
			image.applyFilter(new BlackAndWhiteFilter(blackWhiteThreshold));
			Projection vertical = Projection.vertical(image);
			float peakFoot = settings.getFineSymbolPeakFoot();
			int symbolWidth = settings.getMinSymbolWidth();
			List<Peak> peaks = vertical.findPeaks(peakFoot, symbolWidth);
			Collections.sort(peaks, Peak.WIDTH_COMPARATOR);
			if (peaks.size() == 0) {
				continue;
			}
			Peak peak = peaks.get(0);
			final float threshold = settings.getRusLetterThreshold()
					* vertical.getValues().length;
			CharacterType type = peak.getLeft() >= threshold
					? CharacterType.LATIN : CharacterType.DIGIT;
			Projection horizontal = Projection.horizontal(image.getRegion(0,
					peak.getLeft(), image.getWidth(), peak.getRight()));
			peaks = horizontal.findPeaks(peakFoot, symbolWidth);
			Collections.sort(peaks, Peak.APPEARANCE_COMPARATOR);
			for (Peak p : peaks) {
				Image i = image.getRegion(p.getLeft() + 1, peak.getLeft() + 1,
						p.getRight(), peak.getRight());
				samples.add(new CharacterSample(i, type));
			}
		}
		CharacterRecognizer recognizer = new CharacterRecognizer(PATTERNS);
		List<CharacterPattern> patterns = new ArrayList<CharacterPattern>();
		for (CharacterSample s : samples) {
			patterns.add(recognizer.recognize(s));
		}
		for (RecognizerListener l : listeners) {
			l.characterSamplesCreated(samples);
		}
		return patterns;
	}
}
