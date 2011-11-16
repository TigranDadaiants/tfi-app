package ru.sstu.spheres.core;

import ru.sstu.properties.core.AbstractSettings;
import ru.sstu.properties.core.Property;

/**
 * <code>SphereRecognizerSettings</code> class contains settings for
 * sphere recognizer.
 *
 * @author denis_murashev
 * @since Spheres 1.0
 */
public class SphereRecognizerSettings extends AbstractSettings {

	@Property("radius.min")
	private int minRadius;

	@Property("radius.max")
	private int maxRadius;

	@Property("sigma")
	private float sigma;

	@Property("threshold")
	private float threshold;

	/**
	 * @return the minRadius
	 */
	public int getMinRadius() {
		return minRadius;
	}

	/**
	 * @param minRadius the minRadius to set
	 */
	public void setMinRadius(int minRadius) {
		this.minRadius = minRadius;
	}

	/**
	 * @return the maxRadius
	 */
	public int getMaxRadius() {
		return maxRadius;
	}

	/**
	 * @param maxRadius the maxRadius to set
	 */
	public void setMaxRadius(int maxRadius) {
		this.maxRadius = maxRadius;
	}

	/**
	 * @return the sigma
	 */
	public float getSigma() {
		return sigma;
	}

	/**
	 * @param sigma the sigma to set
	 */
	public void setSigma(float sigma) {
		this.sigma = sigma;
	}

	/**
	 * @return the threshold
	 */
	public float getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
}
