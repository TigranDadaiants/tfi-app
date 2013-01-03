package ru.sstu.lims.core.service.impl;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.lims.core.dao.EmployeeDao;
import ru.sstu.lims.core.dao.PositionDao;
import ru.sstu.lims.core.dao.RoleDao;
import ru.sstu.lims.core.domain.Employee;
import ru.sstu.lims.core.domain.Position;
import ru.sstu.lims.core.domain.Role;
import ru.sstu.lims.core.service.UploadService;
import ru.sstu.tables.AnnotationMapping;
import ru.sstu.tables.ColumnIndex;
import ru.sstu.tables.Mapping;
import ru.sstu.tables.TableException;
import ru.sstu.tables.TableReader;
import ru.sstu.tables.XlsTableReader;
import ru.sstu.tables.XlsxTableReader;

@Service("uploadService")
class UploadServiceImpl implements UploadService {

	private static Logger log = Logger.getLogger(UploadServiceImpl.class);

	private static final Map<DataType, TableReader> READERS
			= new EnumMap<DataType, TableReader>(DataType.class);

	static {
		READERS.put(DataType.XLS, new XlsTableReader());
		READERS.put(DataType.XLSX, new XlsxTableReader());
	}

	@Resource
	private EmployeeDao employeeDao;

	@Resource
	private PositionDao positionDao;

	@Resource
	private RoleDao roleDao;

	@Transactional
	@Override
	public void upload(byte[] data, DataType type) {
		TableReader reader = READERS.get(type);
		loadEmployees(data, reader);
		loadRoles(data, reader);
	}

	private void loadEmployees(byte[] data, TableReader reader) {
		Mapping<EmployeeMapping> employeeMapping
				= new AnnotationMapping<EmployeeMapping>(EmployeeMapping.class);
		employeeMapping.setSkipRows(1);
		employeeMapping.setTableIndex(0);
		Mapping<RoleMapping> roleMapping
				= new AnnotationMapping<RoleMapping>(RoleMapping.class);
		roleMapping.setSkipRows(1);
		roleMapping.setTableIndex(1);
		try {
			List<EmployeeMapping> employees = reader
					.read(employeeMapping, new ByteArrayInputStream(data));
			for (EmployeeMapping e : employees) {
				Position position = positionDao.find(e.position);
				if (position == null) {
					position = new Position();
					position.setName(e.position);
					positionDao.save(position);
				}
				Employee employee = new Employee();
				employee.setFullName(e.fullName);
				employee.setShortName(e.shortName);
				employee.setEnglishName(e.englishName);
				employee.setPosition(position);
				employee.setBirthDate(e.dob);
				employeeDao.save(employee);
			}
		} catch (TableException e) {
			// TODO Notify user
			log.error(e);
		}
	}

	private void loadRoles(byte[] data, TableReader reader) {
		Mapping<RoleMapping> roleMapping
				= new AnnotationMapping<RoleMapping>(RoleMapping.class);
		roleMapping.setSkipRows(1);
		roleMapping.setTableIndex(1);
		try {
			List<RoleMapping> roles = reader
					.read(roleMapping, new ByteArrayInputStream(data));
			for (RoleMapping r : roles) {
				Role role = roleDao.find(r.name);
				if (role == null) {
					role = new Role();
					role.setName(r.name);
					roleDao.save(role);
				}
			}
		} catch (TableException e) {
			// TODO Notify user
			log.error(e);
		}
	}

	/**
	 * Just mapping for {@link Employee} class.
	 * @author denis_murashev
	 */
	public static class EmployeeMapping {
		private static final int FULL_NAME_INDEX = 1;
		private static final int SHORT_NAME_INDEX = 2;
		private static final int ENGLISH_NAME_INDEX = 3;
		private static final int POSITION_INDEX = 4;
		private static final int BIRTH_DATE_INDEX = 6;

		@ColumnIndex(value = FULL_NAME_INDEX)
		private String fullName;
		@ColumnIndex(value = SHORT_NAME_INDEX)
		private String shortName;
		@ColumnIndex(value = ENGLISH_NAME_INDEX)
		private String englishName;
		@ColumnIndex(value = POSITION_INDEX)
		private String position;

		@ColumnIndex(value = BIRTH_DATE_INDEX)
		private Date dob;
	}

	/**
	 * Just mapping for {@link Role} class.
	 * @author denis_murashev
	 */
	public static class RoleMapping {
		@ColumnIndex(value = 0)
		private String name;
	}
}
