package ru.sstu.spheres.core;

import java.util.List;

import ru.sstu.images.analysis.Image;

/**
 * <code>SphereRecognizer</code> class is used for sphere recognition.
 *
 * @author denis_murashev
 * @since Spheres 1.0
 */
public class SphereRecognizer {

	private final SphereRecognizerSettings settings;

	/**
	 * @param settings settings
	 */
	public SphereRecognizer(SphereRecognizerSettings settings) {
		this.settings = settings;
	}

	/**
	 * Recognizes given image.
	 *
	 * @param image image
	 * @return list of recognized spheres
	 */
	public List<Sphere> recognize(Image image) {
		int width = image.getWidth();
		int height = image.getHeight();
		SphereDistributionFunction distribution
				= new SphereDistributionFunction(settings, width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (image.getBrightness(i, j) == 1.0f) {
					distribution.addValue(i, j);
				}
			}
		}
		return distribution.findPeaks();
	}
}
