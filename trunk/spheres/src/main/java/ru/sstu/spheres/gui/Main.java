package ru.sstu.spheres.gui;

import java.io.IOException;
import java.util.Properties;

import ru.sstu.properties.core.PropertyException;
import ru.sstu.spheres.core.SphereRecognizerSettings;

/**
 * <code>Main</code> class is the application starter.
 *
 * @author Denis_Murashev
 * @since Spheres 1.0
 */
public class Main {

	/**
	 * @param args arguments
	 * @throws IOException if cannot load settings
	 * @throws PropertyException if cannot load settings
	 */
	public static void main(String[] args)
			throws IOException, PropertyException {
		SphereRecognizerSettings settings = new SphereRecognizerSettings();
		Properties properties = new Properties();
		properties.load(Main.class.getResourceAsStream("/config.properties"));
		settings.load(properties);
		new SpheresFrame(settings);
	}
}
