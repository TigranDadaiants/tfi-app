package ru.sstu.lims.core.web;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.Position;
import ru.sstu.lims.core.service.ItemService;
import ru.sstu.lims.core.service.PositionService;

/**
 * {@code PositionController} class represents web controller
 * for {@link Position} entities management.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
@Controller("positionBean")
@RequestScoped
public class PositionController extends AbstractItemController<Position> {

	@Resource
	private PositionService positionService;

	@Override
	protected ItemService<Position> getService() {
		return positionService;
	}

	@Override
	protected Position newItem() {
		return new Position();
	}

	@Override
	protected String getListViewId() {
		return "positionsList";
	}

	@Override
	protected String getItemViewId() {
		return "positionInfo";
	}
}
