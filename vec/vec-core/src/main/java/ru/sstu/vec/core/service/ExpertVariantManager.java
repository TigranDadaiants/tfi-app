package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabVariant;

/**
 * {@code ExpertVariantManager} interface describes methods for
 * lab variant objects editing management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface ExpertVariantManager extends ItemManager<LabVariant> {

	/**
	 * Sets current lab.
	 *
	 * @param lab lab
	 */
	void setLab(Lab lab);
}
