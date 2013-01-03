package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.lims.core.dao.EmployeeDao;
import ru.sstu.lims.core.domain.Employee;
import ru.sstu.lims.core.service.EmployeeService;

@Service("employeeService")
class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getAll() {
		return employeeDao.find();
	}

	@Override
	public Employee getById(long id) {
		return employeeDao.findById(id);
	}

	@Override
	public void reload(Employee item) {
	}

	@Transactional
	@Override
	public void save(Employee item) {
		employeeDao.save(item);
	}

	@Transactional
	@Override
	public void delete(Employee item) {
		employeeDao.delete(item);
	}
}
