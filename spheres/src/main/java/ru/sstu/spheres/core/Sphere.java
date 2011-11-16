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
}
