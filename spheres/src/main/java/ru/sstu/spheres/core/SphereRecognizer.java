package ru.sstu.spheres.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.sstu.images.analysis.Image;
import ru.sstu.images.filters.BlackAndWhiteFilter;
import ru.sstu.images.filters.BlurFilter;
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

	private final SphereRecognizerSettings settings;

	private List<SphereRecognizerListener> listeners
			= new ArrayList<SphereRecognizerListener>();

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
		image.applyFilter(new GaussFilter(3, 1));
		image.applyFilter(new SobelFilter());
		image.normalize();
//		image.applyFilter(new GaussFilter(3, 1));
//		image.applyFilter(new MedianFilter());
		image.applyFilter(new BlackAndWhiteFilter(0.1f));
//		image.applyFilter(new BlurFilter());
		image.applyFilter(new MedianFilter());
//		image.applyFilter(new BlackAndWhiteFilter(0.5f));
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
			for (SphereRecognizerListener l : listeners) {
				l.spheresUpdated(new ArrayList<Sphere>(spheres));
			}
		}
		return spheres;
	}
}
