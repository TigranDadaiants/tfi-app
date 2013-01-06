package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabVariant;

/**
 * <code>LabVariantDao</code> interface describes manipulation methods for
 * {@link LabVariant}.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0.1
 */
public interface LabVariantDao extends Dao<LabVariant> {

	/**
	 * Provides variants for lab.
	 *
	 * @param lab lab
	 * @return list of variants
	 */
	List<LabVariant> find(Lab lab);

	/**
	 * Provides variant of lab with given index.
	 *
	 * @param lab   lab
	 * @param index index
	 * @return lab variant
	 */
	LabVariant find(Lab lab, int index);

	/**
	 * Removes variants for given lab.
	 *
	 * @param lab lab
	 */
	void delete(Lab lab);
}
