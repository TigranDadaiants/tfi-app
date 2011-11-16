package ru.sstu.spheres.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ru.sstu.spheres.core.Sphere;

/**
 * <code>ImagePanel</code> class is panel for drawing image with spheres.
 *
 * @author Denis_Murashev
 * @since Spheres 1.0
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 4108668614968199761L;

	private final int width;
	private final int height;
	private BufferedImage image;
	private List<Sphere> spheres = new ArrayList<Sphere>();

	/**
	 * Initializes panel.
	 *
	 * @param width  panel width
	 * @param height panel height
	 */
	public ImagePanel(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @param spheres the spheres to set
	 */
	public void setSpheres(List<Sphere> spheres) {
		this.spheres = spheres;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image != null) {
			AffineTransform transform = new AffineTransform();
			float xRatio = (float) width / image.getWidth();
			float yRatio = (float) height / image.getHeight();
			transform.setToScale(xRatio, yRatio);
			((Graphics2D) g).drawRenderedImage(image, transform);
			g.setColor(Color.RED);
			for (Sphere s : spheres) {
				float x = s.getX() - s.getRadius();
				float y = s.getY() - s.getRadius();
				float size = 2 * s.getRadius();
				g.drawOval((int) (x * xRatio), (int) (y * yRatio),
						(int) (size * xRatio), (int) (size * yRatio));
			}
		}
	}
}
