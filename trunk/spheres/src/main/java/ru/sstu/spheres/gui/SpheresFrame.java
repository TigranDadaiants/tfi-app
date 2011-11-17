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

import ru.sstu.images.analysis.Image;
import ru.sstu.properties.core.PropertyException;
import ru.sstu.properties.gui.SettingsPanel;
import ru.sstu.spheres.core.Sphere;
import ru.sstu.spheres.core.SphereRecognizer;
import ru.sstu.spheres.core.SphereRecognizerListener;
import ru.sstu.spheres.core.SphereRecognizerSettings;

/**
 * <code>SpheresFrame</code> class represents main application frame.
 *
 * @author Denis_Murashev
 * @since Spheres 1.0
 */
public class SpheresFrame extends JFrame implements SphereRecognizerListener {

	private static final long serialVersionUID = 5768669984021604460L;

	private static final int IMAGE_WIDTH = 450;
	private static final int IMAGE_HEIGHT = 500;
	private static final int HISTOGRAM_WIDTH = 450;
	private static final int HISTOGRAM_HEIGHT = 150;

	private ImagePanel original = new ImagePanel(IMAGE_WIDTH, IMAGE_HEIGHT);
	private ImagePanel processed = new ImagePanel(IMAGE_WIDTH, IMAGE_HEIGHT);
	private HistogramPanel histogram = new HistogramPanel(HISTOGRAM_WIDTH,
			HISTOGRAM_HEIGHT);
	private SettingsPanel<SphereRecognizerSettings> settingsPanel;

	private JFileChooser fileChooser = new JFileChooser(".");

	public SpheresFrame(SphereRecognizerSettings settings)
			throws PropertyException {
		ResourceBundle bundle = ResourceBundle.getBundle("spheres");
		setTitle(bundle.getString("spheres.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		final int gap = 5;
		c.insets = new Insets(gap, gap, gap, gap);
		add(original, c);
		c.gridx = 1;
		add(processed, c);
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

	@Override
	public void imageLoaded(Image image) {
		original.setImage(image.createImage());
		repaint();
	}

	@Override
	public void imageProcessed(Image image) {
		processed.setImage(image.createImage());
		repaint();
	}

	@Override
	public void spheresUpdated(List<Sphere> spheres) {
		original.setSpheres(spheres);
		processed.setSpheres(spheres);
		histogram.setSpheres(spheres);
		repaint();
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
			final Image image = new Image(ImageIO
					.read(fileChooser.getSelectedFile()));
			settingsPanel.update();
			SphereRecognizerSettings settings = settingsPanel.getSettings();
			final SphereRecognizer recognizer = new SphereRecognizer(settings);
			recognizer.addListener(this);
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					List<Sphere> spheres = recognizer.recognize(image);
					System.out.println(" Total = " + spheres.size());
					SortedMap<Number, Integer> histogram
							= new TreeMap<Number, Integer>();
					for (Sphere s : spheres) {
						Integer r = histogram.get(s.getRadius());
						if (r == null) {
							r = 0;
						}
						r++;
						histogram.put(s.getRadius(), r);
					}
					for (Number n : histogram.keySet()) {
						System.out.format("%4d : %4d%n", n.intValue(),
								histogram.get(n));
					}
					repaint();
					recognizer.removeListener(SpheresFrame.this);
				}
			};
			new Thread(runnable).start();
		}
	}
}
