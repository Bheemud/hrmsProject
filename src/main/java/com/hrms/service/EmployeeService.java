package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.beans.Employee;
import com.hrms.beans.Employee.Gender;

public interface EmployeeService {


	public List<Employee> getAllEmployee();

	public List<Employee> findByLastName(String lastName);

	public List<Employee> findByFirstName(String firstName);

	public Employee updateLastNameByEmpId(int empId, String lastName);

	public Employee updateFirstNameByEmpId(int empId, String firstName);

	public Employee updateHireDateByEmpId(int empId, String date);

	public Employee updateBirthDateByEmpId(int empId, String date);

	public Employee findEmpByEmpId(int empId);

	public List<Employee> findEmpByBirthDate(Date date);

	public String deleteEmployeeByEmpNo(int empNo);

	List<Employee> getEmployeesByExperience(int yearsOfExperience);

	long getCountByGender(Employee.Gender gender);

	List<Employee> getAllEmployeesByGender(Gender gender);

	int getCountOfEmployeesJoinedLastNYears(int noOfYears);

	public List<Employee> getEmployeesByRole(String role);

	List<Employee> findEmployeesInFifties();

	public List<Employee> getMidAgeEmployees();

	List<String> findAllDistinctRoles();

	Long countWomenEmployees();

	public Employee newEmployee(Employee a);
	
	List<Employee> listOfEmployeesJoinedAfterYear(int year);
	 
    List<Employee> countEmployeesJoinedAfterYear(int year);
 
	public List<Employee> getEmployeesByGender(Gender gender);
 
 
 
	List<Object[]> findManagersAfterDateService();

}
