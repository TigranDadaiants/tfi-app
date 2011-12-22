package ru.sstu.cnr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ru.sstu.cnr.core.CarNumberRecognizer;
import ru.sstu.cnr.core.RecognizerListener;
import ru.sstu.cnr.core.RecognizerSettings;
import ru.sstu.images.analysis.Image;
import ru.sstu.images.analysis.Peak;
import ru.sstu.images.analysis.Projection;
import ru.sstu.ocr.core.CharacterSample;
import ru.sstu.properties.core.PropertyException;
import ru.sstu.properties.core.PropertyHelper;
import ru.sstu.properties.gui.SettingsPanel;

/**
 * <code>ImageFrame</code> class represents GUI for project.
 *
 * @author Denis_Murashev
 */
public class ImageFrame extends JFrame {

	private static final long serialVersionUID = 7922026286519879309L;

	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 700;
	private static final int DEFAULT_PANEL_WIDTH = 350;
	private static final int NARROW_PANEL_HEIGHT = 100;
	private static final int WIDE_PANEL_HEIGHT = 200;
	private static final int GAP = 5;

	private ImagePanel initial = new ImagePanel(DEFAULT_PANEL_WIDTH,
			WIDE_PANEL_HEIGHT);
	private ImagePanel grayed = new ImagePanel(DEFAULT_PANEL_WIDTH,
			WIDE_PANEL_HEIGHT);
	private ImagePanel verticalEdge = new ImagePanel(DEFAULT_PANEL_WIDTH,
			WIDE_PANEL_HEIGHT);
	private ProjectionPanel vProjection = new ProjectionPanel(true);
	private ImagePanel band = new ImagePanel(DEFAULT_PANEL_WIDTH,
			NARROW_PANEL_HEIGHT);
	private ImagePanel horizontalEdge = new ImagePanel(DEFAULT_PANEL_WIDTH,
			NARROW_PANEL_HEIGHT);
	private ProjectionPanel hProjection = new ProjectionPanel(false);
	private ImagesListPanel items = new ImagesListPanel();
	private ImagesListPanel scaledItems = new ImagesListPanel();
	private ImagesListPanel sampledItems = new ImagesListPanel();
	private SettingsPanel<RecognizerSettings> settingsPanel;

	private JFileChooser fileChooser = new JFileChooser(".");

	/**
	 * Initializes frame.
	 *
	 * @throws IOException if some error occurs
	 * @throws PropertyException if some error occurs
	 */
	public ImageFrame() throws IOException, PropertyException {
		super("Images");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setLayout(new BorderLayout(GAP, GAP));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(initial);
		topPanel.add(grayed);
		topPanel.add(verticalEdge);
		topPanel.add(vProjection);
		add(topPanel, BorderLayout.NORTH);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(band);
		leftPanel.add(horizontalEdge);
		leftPanel.add(hProjection);
		add(leftPanel, BorderLayout.WEST);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(items);
		centerPanel.add(scaledItems);
		centerPanel.add(sampledItems);
		add(centerPanel, BorderLayout.CENTER);
		initSettingsPanel();
		setJMenuBar(createMenu());
		pack();
		setVisible(true);
	}

	/**
	 * @param args arguments
	 *
	 * @throws IOException if some error occurs
	 * @throws PropertyException if some error occurs
	 */
	public static void main(String[] args)
			throws IOException, PropertyException {
		new ImageFrame();
	}

	private void initSettingsPanel() throws IOException, PropertyException {
		RecognizerSettings settings = PropertyHelper
				.load(new RecognizerSettings(),
						getClass().getResourceAsStream("/cnr.properties"));
		settingsPanel = new SettingsPanel<RecognizerSettings>(settings, "msg");
		add(settingsPanel, BorderLayout.EAST);
	}

	private JMenuBar createMenu() {
		JMenuItem menuItem = new JMenuItem("Open...");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(ImageFrame.this)
						== JFileChooser.APPROVE_OPTION) {
					openFile(fileChooser.getSelectedFile());
					pack();
					repaint();
				}
			}
		});
		JMenu menu = new JMenu("File");
		menu.add(menuItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		return menuBar;
	}

	private void openFile(File file) {
		try {
			settingsPanel.update();
			CarNumberRecognizer recognizer
					= new CarNumberRecognizer(settingsPanel.getSettings());
			CNRecognizerListener listener = new CNRecognizerListener();
			recognizer.addListener(listener);
			BufferedImage bufferedImaged = ImageIO.read(file);
			initial.setImage(bufferedImaged);
			Image picture = new Image(bufferedImaged);
			String text = recognizer.recognize(picture);
			System.out.println(text);
			JOptionPane.showMessageDialog(ImageFrame.this, text);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ImageFrame.this, e);
		} catch (PropertyException e) {
			JOptionPane.showMessageDialog(ImageFrame.this, e);
		}
	}

	private class CNRecognizerListener implements RecognizerListener {

		@Override
		public void started(Image image) {
		}

		@Override
		public void grayed(Image image) {
			grayed.setImage(image.createImage());
		}

		@Override
		public void verticalEdgesSelected(Image image) {
			verticalEdge.setImage(image.createImage());
		}

		@Override
		public void verticalProjection(Projection projection,
				List<Peak> peaks) {
			vProjection.setProjection(projection);
			vProjection.setWidth(verticalEdge.image.getHeight());
			float max = 1;
			for (Peak p : peaks) {
				if (p.getValue() > max) {
					max = p.getValue();
				}
			}
			vProjection.setHeight(max);
		}

		@Override
		public void horizontalBandSelected(Image image) {
			band.setImage(image.createImage());
		}

		@Override
		public void horizontalEdgesSelected(Image image) {
			horizontalEdge.setImage(image.createImage());
		}

		@Override
		public void horizontalProjection(Projection projection,
				List<Peak> peaks) {
			hProjection.setProjection(projection);
			hProjection.setWidth(horizontalEdge.image.getWidth());
			float max = 1;
			for (Peak p : peaks) {
				if (p.getValue() > max) {
					max = p.getValue();
				}
			}
			hProjection.setHeight(max);
		}

		@Override
		public void symbolImagesSelected(List<Image> images) {
			List<BufferedImage> list = new ArrayList<BufferedImage>();
			for (Image i : images) {
				list.add(i.createImage());
			}
			items.setImages(list);
		}

		@Override
		public void characterSamplesCreated(List<CharacterSample> samples) {
			List<BufferedImage> scaled = new ArrayList<BufferedImage>();
			List<BufferedImage> sampled = new ArrayList<BufferedImage>();
			final int size = 7;
			for (CharacterSample s : samples) {
				scaled.add(s.getImage().createImage());
				sampled.add(s.toImage(size));
			}
			scaledItems.setImages(scaled);
			sampledItems.setImages(sampled);
		}
	}

	private class ImagePanel extends JPanel {

		private static final long serialVersionUID = -2125664199824368293L;

		private final int width;
		private final int height;
		private BufferedImage image;

		/**
		 * @param width  width
		 * @param height height
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

		@Override
		protected void paintComponent(Graphics g) {
			if (image != null) {
				AffineTransform transform = new AffineTransform();
				transform.setToScale((float) width / image.getWidth(),
						(float) height / image.getHeight());
				((Graphics2D) g).drawRenderedImage(image, transform);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}
	}

	private class ProjectionPanel extends JPanel {

		private static final long serialVersionUID = -5248408101795225649L;

		private final boolean inverted;
		private Projection projection;
		private int width;
		private float height;

		public ProjectionPanel(boolean inverted) {
			this.inverted = inverted;
		}

		/**
		 * @param projection the projection to set
		 */
		public void setProjection(Projection projection) {
			this.projection = projection;
		}

		/**
		 * @param width the width to set
		 */
		public void setWidth(int width) {
			this.width = width;
		}

		/**
		 * @param height the height to set
		 */
		public void setHeight(float height) {
			this.height = height;
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (projection != null) {
				g.setColor(Color.RED);
				int x0 = 0;
				int y0 = 0;
				float[] values = projection.getValues();
				int maxX = inverted ? WIDE_PANEL_HEIGHT : DEFAULT_PANEL_WIDTH;
				for (int i = 0; i < width; i++) {
					int x = i * maxX / width;
					int y = (int) (NARROW_PANEL_HEIGHT * values[i] / height);
					if (inverted) {
						g.drawLine(y0, x0, y, x);
					} else {
						g.drawLine(x0, y0, x, y);
					}
					x0 = x;
					y0 = y;
				}
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return inverted
					? new Dimension(NARROW_PANEL_HEIGHT, WIDE_PANEL_HEIGHT)
					: new Dimension(DEFAULT_PANEL_WIDTH, NARROW_PANEL_HEIGHT);
		}
	}

	private class ImagesListPanel extends JPanel {

		private static final long serialVersionUID = 6263606434350497117L;

		private List<BufferedImage> images;

		/**
		 * @param images the images to set
		 */
		public void setImages(List<BufferedImage> images) {
			this.images = images;
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (images != null) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, DEFAULT_PANEL_WIDTH, NARROW_PANEL_HEIGHT);
				int x = 0;
				for (BufferedImage image : images) {
					g.drawImage(image, x, 0, null);
					x += image.getWidth() + GAP;
				}
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(DEFAULT_PANEL_WIDTH, NARROW_PANEL_HEIGHT);
		}
	}
}
