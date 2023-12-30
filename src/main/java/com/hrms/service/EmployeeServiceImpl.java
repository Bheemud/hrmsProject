package com.hrms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.beans.Employee;
import com.hrms.beans.Employee.Gender;
import com.hrms.dao.EmployeeDao;
import com.hrms.exception.EmployeeDataNotFoundException;
import com.hrms.exception.NoEmployeesFoundException;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao empDao;
	
	@Override
	public List<Employee> getAllEmployee() {
		 List<Employee> employees = empDao.findAll();
	        if (employees.isEmpty()) {
	            throw new NoEmployeesFoundException("no employees found");
	        }
	        return employees;
	}
	
	@Override
	public List<Employee> findByLastName(String lastName) {
		if(empDao.findEmployeesByLastName(lastName).isEmpty())
            throw new NoEmployeesFoundException("No LastName found");
		return empDao.findEmployeesByLastName(lastName);
	}

	@Override
	public List<Employee> findByFirstName(String firstName) {
		if(empDao.findEmployeesByFirstName(firstName).isEmpty())
            throw new NoEmployeesFoundException("No FirstName found");
		return empDao.findEmployeesByFirstName(firstName);
	}

	@Override
	public Employee updateLastNameByEmpId(int empId, String lastName) {
	    Optional<Employee> optionalEmployee = empDao.findById(empId);
	    if (optionalEmployee.isPresent()) {
	        Employee emp = optionalEmployee.get();
	        if (lastName != null && !lastName.isEmpty()) {
	            emp.setLastName(lastName);
	            return empDao.save(emp);
	        } else {
	            throw new IllegalArgumentException("Last name cannot be null or empty");
	        }
	    } else {
	        throw new NoEmployeesFoundException("No Employee found for the given EmpId: " + empId);
	    }
	}


	@Override
	public Employee updateFirstNameByEmpId(int empId, String firstName) {
	    Optional<Employee> optionalEmployee = empDao.findById(empId);
	    if (optionalEmployee.isPresent()) {
	        Employee emp = optionalEmployee.get();
	        if (firstName != null) {
	            emp.setFirstName(firstName);
	            return empDao.save(emp);
	        } else {
	            throw new IllegalArgumentException("First name cannot be null");
	        }
	    } else {
	        throw new NoEmployeesFoundException("No Employee found for the given EmpId: " + empId);
	    }
	}


	@Override
	public Employee updateHireDateByEmpId(int empId, String date) {
		
		Optional<Employee> optionalEmployee = empDao.findById(empId);

	    if (optionalEmployee.isPresent()) {
	        Employee emp = optionalEmployee.get();

	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date1 = (Date) dateFormat.parse(date);
	            
	            emp.setHireDate(date1);
	            return empDao.save(emp);
	        } catch (ParseException e) {
	            throw new NoEmployeesFoundException("Given formate is Incorrect");
	        }
	    } else {
            throw new NoEmployeesFoundException("No employees found");
 
	    }
	}

	@Override
	public Employee updateBirthDateByEmpId(int empId, String date) {
		Optional<Employee> optionalEmployee = empDao.findById(empId);

	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();

	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date1 = (Date) dateFormat.parse(date);
	            
	            employee.setHireDate(date1);
	            return empDao.save(employee);
	        } catch (ParseException e) {
	            throw new NoEmployeesFoundException("Given formate is Incorrect");
	        }
	    } else {
            throw new NoEmployeesFoundException("No Employees found");
 
	    }
	}

	

	@Override
	public Employee findEmpByEmpId(int empId) {
	    Optional<Employee> optionalEmployee = empDao.findById(empId);
	    if (optionalEmployee.isPresent()) {
	        return optionalEmployee.get();
	    } else {
	        throw new NoEmployeesFoundException("No employee found for the given EmpId: " + empId);
	    }
	}


	

	@Override
	public List<Employee> findEmpByBirthDate(Date date) {
		if(empDao.findByEmpBirthDate(date).isEmpty())
            throw new NoEmployeesFoundException("No employees Found");
		return empDao.findByEmpBirthDate(date);
	}

	 @Override
	    public String deleteEmployeeByEmpNo(int empNo) {
	        Employee employee = empDao.findByEmpNo(empNo);
	        if (employee != null) {
	            empDao.delete(employee);
	            return "Employee with empNo " + empNo + " is deleted successfully";
	        } else {
	            throw new NoEmployeesFoundException("Employee with empNo " + empNo + " does not exist");

	        }
	    }
	 @Override
	 public List<Employee> getEmployeesByExperience(int yearsOfExperience) {
		 if(empDao.findByEmployeesHireDate(yearsOfExperience).isEmpty())
	            throw new NoEmployeesFoundException("No Experience found");
	        return empDao.findByEmployeesHireDate(yearsOfExperience);
	    }
	 
	 @Override
	    public long getCountByGender(Employee.Gender gender) {
		 if(empDao.countByGender(gender)==0)
	            throw new NoEmployeesFoundException("No Employees found");
	        return empDao.countByGender(gender);
	    }

	@Override
	public List<Employee> getAllEmployeesByGender(Gender gender) {
		
		if(empDao.findByGender(gender).isEmpty())
            throw new NoEmployeesFoundException("no Employees found");
        return empDao.findByGender(gender);
	}
	
	 @Override
	    public int getCountOfEmployeesJoinedLastNYears(int noOfYears) {
		 LocalDate dateNYearsAgo = LocalDate.now().minusYears(noOfYears);
		    return empDao.countByHireDate(dateNYearsAgo);
		}
	 	public static Date convertLocalDateToDate(LocalDate localDate) {
	        return java.sql.Date.valueOf(localDate);
	    }
	 	
	 	@Override
		public List<Employee> getEmployeesByRole(String role) {
	        return empDao.findByRole(role);
		}
	 
		@Override
		public List<Employee> findEmployeesInFifties() {
			return empDao.findEmployeesInFifties();
		}
	 
		@Override
		public List<Employee> getMidAgeEmployees() {
			return empDao.findEmployeesInFifties();
		}
	 
		@Override
		public List<String> findAllDistinctRoles() {
			return empDao.findAllDistinctRoles();
		}
	 
		@Override
		public Long countWomenEmployees() {
			return empDao.countWomenEmployees();
		}

		@Override
		public Employee newEmployee(Employee a) {
			
			return empDao.save(a);
		}
		
		@SuppressWarnings("deprecation")
		@Override
	    public List<Employee> listOfEmployeesJoinedAfterYear(int year) {
	        try {
	            return empDao.findAll()
	                    .stream()
	                    .filter(employee -> employee.getBirthDate().getYear() > year)
	                    .toList();
	        } catch (Exception e) {
	            throw new EmployeeDataNotFoundException("Error retrieving employees"+" "+e);
	        }
	    }
		 @Override
		    public List<Employee> countEmployeesJoinedAfterYear(int year) {
		        try {
		            return empDao.findAll()
		                    .stream()
		                    .filter(employee -> {
		                        int joinYear = employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
		                        return joinYear > year;
		                    })
		                    .toList();
		        } catch (Exception e) {
		            throw new EmployeeDataNotFoundException("Error retrieving employees"+" "+e);
		        }
		    }
		 @Override
		    public List<Employee> getEmployeesByGender(Gender gender) {
		        try {
		            return empDao.findByGender(gender);
		        } catch (Exception e) {
		            throw new EmployeeDataNotFoundException("Error retrieving employees by gender"+" "+e);
		        }
		    }
	 
	 
		 @Override
		 public List<Object[]> findManagersAfterDateService() {
		     List<Object[]> managersList = empDao.findManagersAfterDate();
		     if (managersList == null) {
		         throw new EmployeeDataNotFoundException("Error retrieving Managers after date");
		     }
		     return managersList;
		 }



}
