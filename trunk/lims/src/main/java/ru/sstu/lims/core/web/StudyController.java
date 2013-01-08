package ru.sstu.lims.core.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.Employee;
import ru.sstu.lims.core.domain.LegalStatus;
import ru.sstu.lims.core.domain.Study;
import ru.sstu.lims.core.domain.StudyIdentity;
import ru.sstu.lims.core.service.EmployeeService;
import ru.sstu.lims.core.service.StudyService;

/**
 * {@code StudyController} class represents web controller
 * for {@link Study} entities management.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
@Controller("studyBean")
@RequestScoped
public class StudyController extends AbstractItemController<Study> {

	@Resource
	private StudyService studyService;

	@Resource
	private EmployeeService employeeService;

	/**
	 * @return list of study identities
	 */
	public List<StudyIdentity> getIdentities() {
		return Arrays.asList(StudyIdentity.values());
	}

	/**
	 * @return list of legal statuses
	 */
	public List<LegalStatus> getLegalStatuses() {
		return Arrays.asList(LegalStatus.values());
	}

	/**
	 * @return list of employees
	 */
	public List<Employee> getEmployees() {
		return employeeService.getAll();
	}

	@Override
	protected StudyService getService() {
		return studyService;
	}

	@Override
	protected Study newItem() {
		return new Study();
	}

	@Override
	protected String getListViewId() {
		return "studiesList";
	}

	@Override
	protected String getItemViewId() {
		return "studyInfo";
	}

}
