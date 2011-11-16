package ru.sstu.spheres.core;

import java.util.List;

import ru.sstu.images.analysis.Image;

/**
 * <code>SphereRecognizerListener</code> interface can be used for listening
 * debug information during image recognizing.
 *
 * @author Denis_Murashev
 * @since Spheres 1.0
 */
public interface SphereRecognizerListener {

	/**
	 * The initial image was loaded.
	 *
	 * @param image image
	 */
	void imageLoaded(Image image);

	/**
	 * The image was processed. Edges were detected.
	 *
	 * @param image image
	 */
	void imageProcessed(Image image);

	/**
	 * List of recognized spheres was updated.
	 *
	 * @param spheres updated list of spheres.
	 */
	void spheresUpdated(List<Sphere> spheres);
}
