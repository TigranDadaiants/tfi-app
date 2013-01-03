package ru.sstu.lims.core.web;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.domain.Employee;
import ru.sstu.lims.core.service.EmployeeService;
import ru.sstu.lims.core.service.ItemService;
import ru.sstu.lims.core.service.PositionService;
import ru.sstu.lims.core.service.UploadService;
import ru.sstu.lims.core.service.UploadService.DataType;

/**
 * {@code EmployeeController} class represents web controller
 * for {@link Employee} entities management.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
@Controller("employeeBean")
@RequestScoped
public class EmployeeController extends AbstractItemController<Employee> {

	@Resource
	private EmployeeService employeeService;

	@Resource
	private PositionService positionService;

	@Resource
	private UploadService uploadService;

	/**
	 * Handles file uploading.
	 *
	 * @param event file upload event
	 */
	public void uploadFile(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		int index = file.getFileName().lastIndexOf(".");
		String extension = file.getFileName().substring(index + 1);
		DataType type = DataType.valueOf(extension.toUpperCase());
		if (type != null) {
			uploadService.upload(file.getContents(), type);
		}
	}

	@Override
	protected ItemService<Employee> getService() {
		return employeeService;
	}

	@Override
	protected Employee newItem() {
		return new Employee();
	}

	@Override
	protected String getListViewId() {
		return "employeesList";
	}

	@Override
	protected String getItemViewId() {
		return "employeeInfo";
	}
}
