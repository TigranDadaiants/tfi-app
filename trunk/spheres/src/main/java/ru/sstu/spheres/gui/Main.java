package ru.sstu.spheres.gui;

import ru.sstu.properties.core.PropertyException;
import ru.sstu.properties.core.PropertyHelper;
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
	 * @throws PropertyException if cannot load settings
	 */
	public static void main(String[] args) throws PropertyException {
		new SpheresFrame(PropertyHelper.load(new SphereRecognizerSettings(),
				Main.class.getResourceAsStream("/config.properties")));
	}
}
