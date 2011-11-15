package ru.sstu.cnr.core;

import java.util.Properties;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import ru.sstu.images.analysis.Image;

/**
 * <code>CarNumberRecognizerTest</code> class is unit test for
 * {@link CarNumberRecognizer} class.
 *
 * @author Denis_Murashev
 */
public class CarNumberRecognizerTest extends TestCase {

	// TODO Provide more tests
	private static final String[][] TEST_DATA = {
		{"/0245OK43.png", "0245OK43"},
		{"/A123478.png",  "A123478"},
		{"/AH733147.png", "AH733147"},
		{"/C357BM25.jpg", "C357BM25"},
		{"/K200PT98.jpg", "K200PT98"},
		{"/X777XX77.jpg", "X777XX77"},
		{"/Y559YA99.jpg", "Y559YA99"},
		{"/AE618P29.png", "AE618P29"},
//		{"/AO36578.png",  "AO36578"},
	};

	public void testRecognize() throws Exception {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/cnr.properties"));
		RecognizerSettings settings
				= new RecognizerSettings(properties);
		CarNumberRecognizer recognizer = new CarNumberRecognizer(settings);
		for (String[] number : TEST_DATA) {
			Image image = new Image(ImageIO.read(getClass()
					.getResourceAsStream(number[0])));
			String actual = recognizer.recognize(image);
			String expected = number[1];
			assertEquals(expected, actual);
		}
	}
}
