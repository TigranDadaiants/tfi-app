package ru.sstu.vec.core.web;

import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabVariant;
import ru.sstu.vec.core.service.ExpertVariantManager;

/**
 * {@code ExpertVariantController} class is controller for lab variant
 * editing.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("expertVariantBean")
@SessionScoped
public class ExpertVariantController
		extends AbstractItemController<LabVariant> {

	private static final long serialVersionUID = 3731117026031437747L;

	@Resource
	private ExpertVariantManager expertVariantManager;

	@Resource
	private ExpertLabController expertLabBean;

	@Override
	protected ExpertVariantManager getManager() {
		expertVariantManager.setLab(expertLabBean.getItem());
		return expertVariantManager;
	}

	@Override
	protected LabVariant newItem() {
		LabVariant variant = new LabVariant();
		variant.setLab(expertLabBean.getItem());
		return variant;
	}

	@Override
	protected String getListViewId() {
		return "expertLabInfo";
	}

	@Override
	protected String getItemViewId() {
		return "expertVariantInfo";
	}
}
