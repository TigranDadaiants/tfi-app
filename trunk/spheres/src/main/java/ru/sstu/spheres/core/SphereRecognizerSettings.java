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

	@Property("threshold.peak.probability")
	private float peakThreshold;

	@Property("threshold.black.white")
	private float blackWhiteThreshold;

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
	 * @return the peakThreshold
	 */
	public float getPeakThreshold() {
		return peakThreshold;
	}

	/**
	 * @param peakThreshold the peakThreshold to set
	 */
	public void setPeakThreshold(float peakThreshold) {
		this.peakThreshold = peakThreshold;
	}

	/**
	 * @return the blackWhiteThreshold
	 */
	public float getBlackWhiteThreshold() {
		return blackWhiteThreshold;
	}

	/**
	 * @param blackWhiteThreshold the blackWhiteThreshold to set
	 */
	public void setBlackWhiteThreshold(float blackWhiteThreshold) {
		this.blackWhiteThreshold = blackWhiteThreshold;
	}
}
