package ru.sstu.spheres;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ru.sstu.images.analysis.Image;
import ru.sstu.images.filters.BlackAndWhiteFilter;
import ru.sstu.images.filters.edges.SobelFilter;
import ru.sstu.spheres.core.Sphere;
import ru.sstu.spheres.core.SphereRecognizer;
import ru.sstu.spheres.core.SphereRecognizerSettings;
import ru.sstu.spheres.gui.ImagePanel;

public class Main {

	private static final float BLACK_AND_WHITE_THREASHOLD = 0.8f;

	private static List<Sphere> circles = new ArrayList<Sphere>();

	public static void main(String[] args) throws IOException {
		Image original = new Image(ImageIO.read(new File("samples/1.jpg")));
		Image image = new Image(original.createImage());
		image.normalize();
//		image.applyFilter(new MedianFilter());
//		Filter gaussFilter = new GaussFilter(3, 1);
//		image.applyFilter(gaussFilter);
//		image.applyFilter(new SuperEdgeDetector());
		image.applyFilter(new SobelFilter());
//		image.applyFilter(new BlackAndWhiteFilter(BLACK_AND_WHITE_THREASHOLD));
//		image.applyFilter(new MedianFilter());
//		image.applyFilter(gaussFilter);
		image.applyFilter(new BlackAndWhiteFilter(0.4f));
		SphereRecognizerSettings config = new SphereRecognizerSettings();
		config.setMinRadius(10);
		config.setMaxRadius(15);
		config.setSigma(1);
		config.setThreshold(3.0f);
		SphereRecognizer recognizer = new SphereRecognizer(config);
		circles = recognizer.recognize(image);
		System.out.println(" Total = " + circles.size());
		showImage(original, image);
		SortedMap<Number, Integer> histogram = new TreeMap<Number, Integer>();
		for (Sphere s : circles) {
			Integer r = histogram.get(s.getRadius());
			if (r == null) {
				r = 0;
			}
			r++;
			histogram.put(s.getRadius(), r);
		}
		for (Number n : histogram.keySet()) {
			System.out.format("%4d : %4d%n", n.intValue(), histogram.get(n));
		}
	}

	private static void showImage(Image original, Image edged) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1.0;
		c.weighty = 1.0;
		ImagePanel panel = new ImagePanel(500, 700);
		panel.setImage(original.createImage());
		frame.add(panel, c);
		panel = new ImagePanel(500, 700);
		panel.setImage(edged.createImage());
		c.gridx++;
		c.anchor = GridBagConstraints.NORTHEAST;
		frame.add(panel, c);
		frame.pack();
		frame.setVisible(true);
	}
}
