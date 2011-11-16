package ru.sstu.spheres.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ru.sstu.images.analysis.Image;
import ru.sstu.images.filters.BlackAndWhiteFilter;
import ru.sstu.images.filters.edges.SobelFilter;
import ru.sstu.properties.core.PropertyException;
import ru.sstu.properties.gui.SettingsPanel;
import ru.sstu.spheres.core.Sphere;
import ru.sstu.spheres.core.SphereRecognizer;
import ru.sstu.spheres.core.SphereRecognizerSettings;

public class SpheresFrame extends JFrame {

	private static final int IMAGE_WIDTH = 500;
	private static final int IMAGE_HEIGHT = 700;

	private ImagePanel original = new ImagePanel(IMAGE_WIDTH, IMAGE_HEIGHT);
	private ImagePanel processed = new ImagePanel(IMAGE_WIDTH, IMAGE_HEIGHT);
	private HistogramPanel histogram = new HistogramPanel();
	private SettingsPanel<SphereRecognizerSettings> settingsPanel;

	private JFileChooser fileChooser = new JFileChooser(".");

	public SpheresFrame(SphereRecognizerSettings settings)
			throws PropertyException {
		ResourceBundle bundle = ResourceBundle.getBundle("spheres");
		setTitle(bundle.getString("spheres.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		final int gap = 5;
		c.insets = new Insets(gap, gap, gap, gap);
		add(original, c);
		c.gridx = 1;
		add(processed, c);
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		add(histogram, c);
		c.gridx = 1;
		settingsPanel = new SettingsPanel<SphereRecognizerSettings>(settings,
				"spheres");
		add(settingsPanel, c);
		setJMenuBar(createMenuBar(bundle));
		pack();
		setVisible(true);
	}

	private JMenuBar createMenuBar(ResourceBundle bundle) {
		JMenuItem openItem = new JMenuItem(bundle.getString("menu.open"));
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					openFile();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(SpheresFrame.this, ex);
				} catch (PropertyException ex) {
					JOptionPane.showMessageDialog(SpheresFrame.this, ex);
				}
			}
		});

		JMenuItem exitItem = new JMenuItem(bundle.getString("menu.exit"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu fileMenu = new JMenu(bundle.getString("menu.file"));
		fileMenu.add(openItem);
		fileMenu.add(exitItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		return menuBar;
	}

	private void openFile() throws IOException, PropertyException {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Image image = new Image(ImageIO
					.read(fileChooser.getSelectedFile()));
			original.setImage(image.createImage());
			image.normalize();
//			image.applyFilter(new MedianFilter());
//			Filter gaussFilter = new GaussFilter(3, 1);
//			image.applyFilter(gaussFilter);
//			image.applyFilter(new SuperEdgeDetector());
			image.applyFilter(new SobelFilter());
//			image.applyFilter(new BlackAndWhiteFilter(BLACK_AND_WHITE_THREASHOLD));
//			image.applyFilter(new MedianFilter());
//			image.applyFilter(gaussFilter);
			image.applyFilter(new BlackAndWhiteFilter(0.4f));
			processed.setImage(image.createImage());
			settingsPanel.update();
			SphereRecognizerSettings settings = settingsPanel.getSettings();
			SphereRecognizer recognizer = new SphereRecognizer(settings);
			List<Sphere> spheres = recognizer.recognize(image);
			original.setSpheres(spheres);
			processed.setSpheres(spheres);
			System.out.println(" Total = " + spheres.size());
			SortedMap<Number, Integer> histogram = new TreeMap<Number, Integer>();
			for (Sphere s : spheres) {
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
			repaint();
		}
	}

	private static class HistogramPanel extends JPanel {
		
	}
}
