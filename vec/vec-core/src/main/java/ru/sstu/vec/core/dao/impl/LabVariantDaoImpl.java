package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LabVariantDao;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabVariant;

/**
 * <code>LabVariantDaoImpl</code> class is DAO implementation for
 * {@link LabVariant}.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("labVariantDao")
class LabVariantDaoImpl extends GenericDao<LabVariant>
		implements LabVariantDao {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 4845384113561611852L;

	/**
	 * Just string for lab.
	 */
	private static final String LAB = "lab";

	/**
	 * {@inheritDoc}
	 */
	public List<LabVariant> find(Lab lab) {
		return list(getCriteria().add(Restrictions.eq(LAB, lab)));
	}

	/**
	 * {@inheritDoc}
	 */
	public LabVariant find(Lab lab, int index) {
		return unique(getCriteria()
				.add(Restrictions.eq(LAB, lab))
				.add(Restrictions.eq("index", index)));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Lab lab) {
		for (LabVariant v : find(lab)) {
			delete(v);
		}
	}
}
