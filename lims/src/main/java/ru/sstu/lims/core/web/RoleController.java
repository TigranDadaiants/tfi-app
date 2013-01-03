package ru.sstu.lims.core.web;

import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.Role;
import ru.sstu.lims.core.service.ItemService;
import ru.sstu.lims.core.service.RoleService;

/**
 * {@code RoleController} class represents web controller
 * for {@link Role} entities management.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
@Controller("roleBean")
@SessionScoped
public class RoleController extends AbstractItemController<Role> {

	@Resource
	private RoleService roleService;

	@Override
	protected ItemService<Role> getService() {
		return roleService;
	}

	@Override
	protected Role newItem() {
		return new Role();
	}

	@Override
	protected String getListViewId() {
		return "rolesList";
	}

	@Override
	protected String getItemViewId() {
		return "roleInfo";
	}
}
