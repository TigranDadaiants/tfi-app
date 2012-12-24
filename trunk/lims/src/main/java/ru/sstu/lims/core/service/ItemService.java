package ru.sstu.lims.core.service;

import java.io.Serializable;
import java.util.List;

public interface ItemService<T extends Serializable> {

	List<T> getAll();

	T getById(long id);

	void reload(T user);

	void save(T user);

	void delete(T user);
}