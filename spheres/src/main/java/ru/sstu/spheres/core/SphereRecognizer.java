package ru.sstu.spheres.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.sstu.images.analysis.Image;
import ru.sstu.images.filters.BlackAndWhiteFilter;
import ru.sstu.images.filters.GaussFilter;
import ru.sstu.images.filters.MedianFilter;
import ru.sstu.images.filters.edges.SobelFilter;

/**
 * <code>SphereRecognizer</code> class is used for sphere recognition.
 *
 * @author denis_murashev
 * @since Spheres 1.0
 */
public class SphereRecognizer {

	private static final int GAUSS_FILTER_MATRIX_SIZE = 3;
	private static final float GAUSS_FILTER_SIGMA = 1.4f;
	private static final GaussFilter GAUSS_FILTER
			= new GaussFilter(GAUSS_FILTER_MATRIX_SIZE, GAUSS_FILTER_SIGMA);

	private final SphereRecognizerSettings settings;

	private List<SphereRecognizerListener> listeners
			= new ArrayList<SphereRecognizerListener>();

	private volatile boolean stopped;

	/**
	 * @param settings settings
	 */
	public SphereRecognizer(SphereRecognizerSettings settings) {
		this.settings = settings;
	}

	/**
	 * Adds new listener.
	 *
	 * @param listener listener
	 */
	public void addListener(SphereRecognizerListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes listener.
	 *
	 * @param listener listener
	 */
	public void removeListener(SphereRecognizerListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Recognizes given image.
	 *
	 * @param image image
	 * @return list of recognized spheres
	 */
	public List<Sphere> recognize(Image image) {
		for (SphereRecognizerListener l : listeners) {
			l.imageLoaded(image);
		}
		image.normalize();
		image.applyFilter(GAUSS_FILTER);
		image.applyFilter(new SobelFilter());
		image.applyFilter(new BlackAndWhiteFilter(settings
				.getBlackWhiteThreshold()));
		image.applyFilter(new MedianFilter());
		for (SphereRecognizerListener l : listeners) {
			l.imageProcessed(image);
		}
		int width = image.getWidth();
		int height = image.getHeight();
		List<Sphere> spheres = new LinkedList<Sphere>();
		for (int r = settings.getMinRadius();
				r <= settings.getMaxRadius();
				r++) {
			DistributionFunction distribution
					= new DistributionFunction(settings, r, width, height);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (image.getBrightness(i, j) == 1.0f) {
						distribution.addValue(i, j);
					}
				}
			}
			spheres = distribution.findPeaks(spheres);
			if (stopped) {
				break;
			}
			for (SphereRecognizerListener l : listeners) {
				l.spheresUpdated(new ArrayList<Sphere>(spheres));
			}
		}
		listeners.clear();
		return spheres;
	}

	/**
	 * Schedules calculation process stopping.
	 */
	public void stop() {
		stopped = true;
	}
}
