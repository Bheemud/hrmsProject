package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hrms.beans.Employee;
import com.hrms.beans.Employee.Gender;
import com.hrms.beans.controller.EmployeeApi;
import com.hrms.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
 class EmployeeApiTest {
	@InjectMocks
	private EmployeeApi employeeApi;
	@Mock
	private EmployeeServiceImpl employeeServiceImpl;

	@BeforeEach
	 void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	 void testGetListOfEmp() {
		List<Employee> expectedEmployeeList = new ArrayList<>(); // Set your expected result

		when(employeeServiceImpl.getAllEmployee()).thenReturn(expectedEmployeeList);

		List<Employee> actualEmployeeList = employeeApi.getListOfEmp();

		verify(employeeServiceImpl, times(1)).getAllEmployee();
		assertEquals(expectedEmployeeList, actualEmployeeList);
	}

	@Test
	 void testGetEmpByFirstName() {
		String firstName = "John"; // Set your test data

		List<Employee> expectedResults = new ArrayList<>(); // Set your expected results

		when(employeeServiceImpl.findByFirstName(firstName)).thenReturn(expectedResults);

		employeeApi.getEmpByFirstName(firstName);

		verify(employeeServiceImpl, times(1)).findByFirstName(firstName);
	}

	@Test
	 void testGetEmpByLastName() {
		String lastName = "Doe"; // Set your test data

		List<Employee> expectedResults = new ArrayList<>(); // Set your expected results

		when(employeeServiceImpl.findByLastName(lastName)).thenReturn(expectedResults);

		 employeeApi.getEmpByLastName(lastName);

		verify(employeeServiceImpl, times(1)).findByLastName(lastName);
	}

	@Test
	 void testUpdateFirstNameByEmpId() {
		int empId = 123; 
		String firstName = "John"; 

		Employee updatedEmployee = new Employee(); 

		when(employeeServiceImpl.updateFirstNameByEmpId(empId, firstName)).thenReturn(updatedEmployee);

		 employeeApi.updateFirstNameByEmpId(empId, firstName);

		verify(employeeServiceImpl, times(1)).updateFirstNameByEmpId(empId, firstName);
	}

	@SuppressWarnings("unused")
	@Test
	 void testUpdateLastNameByEmpId() {
	
		int empId = 123; 
		String lastName = "Doe"; 

		Employee inputEmployee = new Employee();
		Employee expectedUpdatedEmployee = new Employee();

		when(employeeServiceImpl.updateLastNameByEmpId(empId, lastName)).thenReturn(expectedUpdatedEmployee);

		Employee actualUpdatedEmployee = employeeApi.updateLastNameByEmpId(empId, lastName);

		verify(employeeServiceImpl, times(1)).updateLastNameByEmpId(empId, lastName);
		assertEquals(expectedUpdatedEmployee, actualUpdatedEmployee);
	}

	@Test
	 void testUpdateHireDateByEmpId() {
		int empId = 123; 
		String date = "2023-12-09"; 

		Employee expectedUpdatedEmployee = new Employee(); 

		when(employeeServiceImpl.updateHireDateByEmpId(empId, date)).thenReturn(expectedUpdatedEmployee);

		
		employeeApi.updateHireDateByEmpId(empId, date);

		
		verify(employeeServiceImpl, times(1)).updateHireDateByEmpId(empId, date);
	}

	@Test
	 void testUpdateBirthDayByEmpId() {
		int empId = 123; 
		String newBirthdate = "2023-12-31"; 

		Employee mockedResult = new Employee();

		when(employeeServiceImpl.updateBirthDateByEmpId(empId, newBirthdate)).thenReturn(mockedResult);

		employeeApi.updateBirthDayByEmpId(empId, newBirthdate);

		verify(employeeServiceImpl, times(1)).updateBirthDateByEmpId(empId, newBirthdate);
	}

	@Test
	 void testGetAllEmployeesByGender() {
		Gender gender = Gender.M; 

		List<Employee> expectedEmployees = Arrays.asList();
		when(employeeServiceImpl.getAllEmployeesByGender(gender)).thenReturn(expectedEmployees);

		ResponseEntity<List<Employee>> responseEntity = employeeApi.getAllEmployeesByGender(gender);

		verify(employeeServiceImpl, times(1)).getAllEmployeesByGender(gender);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedEmployees, responseEntity.getBody());
	}

	@Test
	 void testGetEmpById() {
		int empId = 123; 
		Employee expectedEmployee = new Employee();

		when(employeeServiceImpl.findEmpByEmpId(empId)).thenReturn(expectedEmployee);

		Employee actualEmployee = employeeApi.getEmpById(empId);

		verify(employeeServiceImpl, times(1)).findEmpByEmpId(empId);
		assertEquals(expectedEmployee, actualEmployee);
	}

	@Test
	 void testGetByBirthDay() {
		Date birthdate = new Date(System.currentTimeMillis()); 

		List<Employee> expectedResults = new ArrayList<>(); 

		when(employeeServiceImpl.findEmpByBirthDate(birthdate)).thenReturn(expectedResults);

		 employeeApi.getByBirthDay(birthdate);

		verify(employeeServiceImpl, times(1)).findEmpByBirthDate(birthdate);
	}

	@Test
	 void testDeleteEmployeeByEmpNo() {
		int empNo = 123; 

		String expectedResult = "Employee deleted successfully"; 

		when(employeeServiceImpl.deleteEmployeeByEmpNo(empNo)).thenReturn(expectedResult);

		ResponseEntity<String> responseEntity = employeeApi.deleteEmployeeByEmpNo(empNo);

		verify(employeeServiceImpl, times(1)).deleteEmployeeByEmpNo(empNo);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedResult, responseEntity.getBody());
	}

	@Test
	 void testGetEmployeesByExperience() {
	
		int yearsOfExperience = 5; 

		List<Employee> expectedEmployees = new ArrayList<>(); 

		when(employeeServiceImpl.getEmployeesByExperience(yearsOfExperience)).thenReturn(expectedEmployees);

		ResponseEntity<List<Employee>> responseEntity = employeeApi.getEmployeesByExperience(yearsOfExperience);

		verify(employeeServiceImpl, times(1)).getEmployeesByExperience(yearsOfExperience);
		if (expectedEmployees.isEmpty()) {
			assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		} else {
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			assertEquals(expectedEmployees, responseEntity.getBody());
		}
	}

	@Test
	 void testGetCountByGender() {
		Employee.Gender gender = Employee.Gender.M; 

		long expectedCount = 10; 
		when(employeeServiceImpl.getCountByGender(gender)).thenReturn(expectedCount);

		long actualCount = employeeApi.getCountByGender(gender);

		verify(employeeServiceImpl, times(1)).getCountByGender(gender);
		assertEquals(expectedCount, actualCount);
	}

	@Test
	 void testGetCountOfEmployeesJoinedLastNYears() {
		int noOfYears = 5;

		int expectedCount = 15; 
		when(employeeServiceImpl.getCountOfEmployeesJoinedLastNYears(noOfYears)).thenReturn(expectedCount);

		int actualCount = employeeApi.getCountOfEmployeesJoinedLastNYears(noOfYears);

		verify(employeeServiceImpl, times(1)).getCountOfEmployeesJoinedLastNYears(noOfYears);
		assertEquals(expectedCount, actualCount);
	}

	@Test
	 void testGetEmployeesByRole() {
		String role = "Developer"; 

		List<Employee> expectedEmployees = new ArrayList<>(); 

		when(employeeServiceImpl.getEmployeesByRole(role)).thenReturn(expectedEmployees);

		List<Employee> actualEmployees = employeeServiceImpl.getEmployeesByRole(role);

		verify(employeeServiceImpl, times(1)).getEmployeesByRole(role);
		assertEquals(expectedEmployees, actualEmployees);
	}

	@Test
	 void testGetMidAgeEmployees() {
		List<Employee> expectedMidAgeEmployees = new ArrayList<>(); 

		when(employeeServiceImpl.getMidAgeEmployees()).thenReturn(expectedMidAgeEmployees);

		employeeApi.getMidAgeEmployees();

		verify(employeeServiceImpl, times(1)).getMidAgeEmployees();
	}

	@Test
	 void testFindAllDistinctRoles() {
		List<String> expectedDistinctRoles = new ArrayList<>(); 

		when(employeeServiceImpl.findAllDistinctRoles()).thenReturn(expectedDistinctRoles);

		 employeeApi.findAllDistinctRoles();

		verify(employeeServiceImpl, times(1)).findAllDistinctRoles();
	}

	@Test
	 void testCountWomenEmployees() {
		long expectedCount = 42;
		when(employeeServiceImpl.countWomenEmployees()).thenReturn(expectedCount);

		employeeApi.countWomenEmployees();

		verify(employeeServiceImpl, times(1)).countWomenEmployees();
	}

	@Test
	 void testAddNewEmployee() {
		Employee inputEmployee = new Employee();
		Employee expectedEmployee = new Employee();

		when(employeeServiceImpl.newEmployee(inputEmployee)).thenReturn(expectedEmployee);

		employeeApi.addNewEmployee(inputEmployee);

		verify(employeeServiceImpl, times(1)).newEmployee(inputEmployee);
	}

	@Test
	 void testListOfEmployeesJoinedAfterYear() {
		int year = 2022; 

		List<Employee> expectedEmployees = new ArrayList<>(); 

		when(employeeServiceImpl.listOfEmployeesJoinedAfterYear(year)).thenReturn(expectedEmployees);

		employeeApi.listOfEmployeesJoinedAfterYear(year);

		verify(employeeServiceImpl, times(1)).listOfEmployeesJoinedAfterYear(year);
	}

	

	

	@Test
	 void testFindManagersAfterDateService() {
		Date fromDate = Date.valueOf("2023-01-01"); 
		List<Object[]> expectedResults = new ArrayList<>();

		when(employeeServiceImpl.findManagersAfterDateService()).thenReturn(expectedResults);

		List<Object[]> actualResults = employeeApi.findManagersAfterDateService(fromDate);

		verify(employeeServiceImpl, times(1)).findManagersAfterDateService();
		assertEquals(expectedResults, actualResults);
	}

	
	@Test
     void testCountEmployeesJoinedAfterYear() {
        int year = 2022;

        List<Employee> expectedEmployees = new ArrayList<>();
        Mockito.when(employeeApi.countEmployeesJoinedAfterYear(year)).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApi.countEmployeesJoinedAfterYear(year);

        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
     void testGetEmployeesByGender() {
        Gender gender = Gender.M;

        List<Employee> expectedEmployees = new ArrayList<>();
        Mockito.when(employeeApi.getEmployeesByGender(gender)).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApi.getEmployeesByGender(gender);

        assertEquals(expectedEmployees, actualEmployees);
    }
    
   
}