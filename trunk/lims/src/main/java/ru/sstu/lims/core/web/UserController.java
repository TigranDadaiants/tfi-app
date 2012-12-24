package ru.sstu.lims.core.web;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.User;
import ru.sstu.lims.core.service.ItemService;
import ru.sstu.lims.core.service.UserService;

@Controller("userBean")
@RequestScoped
public class UserController extends AbstractItemController<User> {

	@Resource
	private UserService userService;

	@Override
	protected ItemService<User> getService() {
		return userService;
	}

	@Override
	protected User newItem() {
		return new User();
	}

	@Override
	protected String getListViewId() {
		return "usersList";
	}

	@Override
	protected String getItemViewId() {
		return "userInfo";
	}
}
