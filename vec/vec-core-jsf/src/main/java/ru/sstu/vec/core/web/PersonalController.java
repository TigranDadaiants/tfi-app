package ru.sstu.vec.core.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <code>PersonalController</code> class controls personal settings
 * manipulations.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("personal")
@Scope("request")
public class PersonalController extends VecController {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 551064133038893332L;
}
