package ru.sstu.spheres.core;

/**
 * <code>Sphere</code> class represents sphere model.
 *
 * @author denis_murashev
 * @since Spheres 1.0
 */
public class Sphere {

	private final float x;
	private final float y;
	private final float radius;
	private float probability;

	/**
	 * @param x      x of center
	 * @param y      y of center
	 * @param radius radius
	 */
	public Sphere(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * @return the probability
	 */
	public float getProbability() {
		return probability;
	}

	/**
	 * @param probability the probability to set
	 */
	public void setProbability(float probability) {
		this.probability = probability;
	}

	/**
	 * Tests if given sphere is probably the same.
	 *
	 * @param sphere sphere to be tested
	 * @param sigma the sigma
	 * @return test result
	 */
	public boolean isSame(Sphere sphere, float sigma) {
		// FIXME
		return Math.abs(x - sphere.x) < sigma
				&& Math.abs(y - sphere.y) < sigma
				&& Math.abs(radius - sphere.radius) < sigma;
	}
}
