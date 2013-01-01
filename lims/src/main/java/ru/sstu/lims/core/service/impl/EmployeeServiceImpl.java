package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	@Override
	public void save(Employee item) {
		employeeDao.save(item);
	}

	@Override
	public void delete(Employee item) {
		employeeDao.delete(item);
	}
}
