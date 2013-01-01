package ru.sstu.lims.core.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.Employee;
import ru.sstu.lims.core.service.EmployeeService;

/**
 * {@code ReportController} class is just sample controller for reports.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Controller("reportBean")
@RequestScoped
public class ReportController {

	@Resource
	private EmployeeService employeeService;

	/**
	 * @return list of employees
	 */
	public List<Employee> getEmployees() {
		return employeeService.getAll();
	}

	/**
	 * @return average age of employees
	 */
	public int getAverageAge() {
		List<Employee> employees = getEmployees();
		int count = 0;
		long sum = 0;
		for (Employee e : employees) {
			sum += e.getBirthDate().getTime();
			count++;
		}
		long time = sum / count;
		Date date = new Date(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return Calendar.getInstance().get(Calendar.YEAR)
				- calendar.get(Calendar.YEAR);
	}
}
