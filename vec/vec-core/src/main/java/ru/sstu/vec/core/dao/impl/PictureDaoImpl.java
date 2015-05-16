package ru.sstu.vec.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.PictureDao;
import ru.sstu.vec.core.domain.Picture;

@Repository("pictureDao")
class PictureDaoImpl extends GenericDao<Picture> implements PictureDao {

	private static final long serialVersionUID = 4789321015473533282L;
}
