package ru.sstu.lims.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.EmployeeDao;
import ru.sstu.lims.core.domain.Employee;

@Repository("employeeDao")
class EmployeeDaoImpl extends GenericDao<Employee> implements EmployeeDao {

	private static final long serialVersionUID = 7734381153167672144L;
}
