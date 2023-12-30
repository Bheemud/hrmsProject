package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.beans.Employee;
import com.hrms.dao.EmployeeDao;
import com.hrms.exception.NoEmployeesFoundException;
import com.hrms.service.EmployeeServiceImpl;

@SpringBootTest
class EmployeeServiceTest {

	@Mock
	private EmployeeDao empDaoMock;

	@InjectMocks
	private EmployeeServiceImpl empService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@BeforeEach
	void testGetAllEmployee() {
		String lastName = "NonExistingLastName";

		when(empDaoMock.findEmployeesByLastName(lastName)).thenReturn(List.of());

		try {
			empService.findByLastName(lastName);
			fail("Expected NoEmployeesFoundException was not thrown");
		} catch (NoEmployeesFoundException e) {
			assertEquals("No LastName found", e.getMessage());
		}

	}

	@Test
	void testFindByLastName() {
		String lastName = "Doe";
		List<Employee> mockEmployees = new ArrayList<>();
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();
		mockEmployees.add(employee1);
		mockEmployees.add(employee2);
		when(empDaoMock.findEmployeesByLastName(lastName)).thenReturn(mockEmployees);
		List<Employee> employees = empService.findByLastName(lastName);
		assertNotNull(employees);
		assertEquals(2, employees.size()); // Ensure the correct number of employees is returned
	}

	@Test
	void testUpdateLastNameByEmpId() {
	    int empId = 123;
	    String lastName = "NewLastName";

	    Employee employee = new Employee();
	    // Assume the employee's last name is initially set to a different value
	    employee.setLastName("OldLastName");

	    when(empDaoMock.findById(empId)).thenReturn(Optional.of(employee));

	    Employee updatedEmployee = empService.updateLastNameByEmpId(empId, lastName);

	    // Assert that the last name of the updated employee matches the expected value
	    assertEquals(lastName, updatedEmployee.getLastName());
	}


	@Test
	void testUpdateHireDateByEmpId() throws ParseException {
	    int empId = 123;
	    String date = "2023-12-31";

	    // Parse the string date into a Date object
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedDate = (Date) dateFormat.parse(date);

	    Employee employee = new Employee();

	    when(empDaoMock.findById(empId)).thenReturn(Optional.of(employee));

	    Employee updatedEmployee = empService.updateHireDateByEmpId(empId, date);

	    // Assert that the updated employee's hire date matches the provided date
	    assertEquals(parsedDate, updatedEmployee.getHireDate());
	}
	@Test
	 void testUpdateHireDateByEmpId1() throws ParseException {
		int empId = 1;
		String hireDate = "2023-01-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = dateFormat.parse(hireDate);
		Employee mockEmployee = new Employee();
		mockEmployee.setEmpNo(empId);
		mockEmployee.setHireDate(parsedDate);

		when(empDaoMock.findById(empId)).thenReturn(Optional.of(mockEmployee));
		when(empDaoMock.save(any())).thenReturn(mockEmployee);

		Employee result = empService.updateHireDateByEmpId(empId, hireDate);

		verify(empDaoMock, times(1)).findById(empId);
		verify(empDaoMock, times(1)).save(mockEmployee);

		assertNotNull(result);
		assertEquals(empId, result.getEmpNo());
		assertEquals(parsedDate, result.getHireDate());
	}

	@Test
	 void testUpdateHireDateByEmpIdNoEmployeeFound() {
		int empId = 1;
		String hireDate = "2023-01-01";

		when(empDaoMock.findById(empId)).thenReturn(Optional.empty());

		assertThrows(NoEmployeesFoundException.class, () -> empService.updateHireDateByEmpId(empId, hireDate));

		verify(empDaoMock, times(1)).findById(empId);
		verify(empDaoMock, never()).save(any());
	}

	@Test
	 void testUpdateHireDateByEmpIdInvalidDateFormat() {
		int empId = 1;
		String invalidHireDate = "invalid-date";

		when(empDaoMock.findById(empId)).thenReturn(Optional.of(new Employee()));

		assertThrows(NoEmployeesFoundException.class, () -> empService.updateHireDateByEmpId(empId, invalidHireDate));

		verify(empDaoMock, times(1)).findById(empId);
		verify(empDaoMock, never()).save(any());
	}

	@Test
	 void testUpdateBirthDateByEmpId() throws ParseException {
		int empId = 1;
		String birthDate = "2000-01-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = dateFormat.parse(birthDate);
		Employee mockEmployee = new Employee();
		mockEmployee.setEmpNo(empId);
		mockEmployee.setBirthDate(parsedDate);

		when(empDaoMock.findById(empId)).thenReturn(Optional.of(mockEmployee));
		when(empDaoMock.save(any())).thenReturn(mockEmployee);

		Employee result = empService.updateBirthDateByEmpId(empId, birthDate);

		verify(empDaoMock, times(1)).findById(empId);
		verify(empDaoMock, times(1)).save(mockEmployee);

		assertNotNull(result);
		assertEquals(empId, result.getEmpNo());
		assertEquals(parsedDate, result.getBirthDate());
	}

	@Test
	 void testUpdateBirthDateByEmpIdNoEmployeeFound() {
		int empId = 1;
		String birthDate = "2000-01-01";

		when(empDaoMock.findById(empId)).thenReturn(Optional.empty());

		assertThrows(NoEmployeesFoundException.class, () -> empService.updateBirthDateByEmpId(empId, birthDate));

		verify(empDaoMock, times(1)).findById(empId);
		verify(empDaoMock, never()).save(any());
	}

}