package com.hrms.beans.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.Employee;
import com.hrms.beans.Employee.Gender;
import com.hrms.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/employee")
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeApi {

	@Autowired
    private  EmployeeService empSer;

	@GetMapping
	public List<Employee> getListOfEmp(){
		return empSer.getAllEmployee();
	}
	
	@GetMapping("/fetch/firstName/{fn}")
	public List<Employee> getEmpByFirstName(@PathVariable("fn") String name){
		return empSer.findByFirstName(name);
	}
	
	@GetMapping("/fetch/lastName/{lastn}")
	public List<Employee> getEmpByLastName(@PathVariable("lastn") String name){
		return empSer.findByLastName(name);
	}
	
	@PutMapping("/update/firstname/{empId}")
	public Employee updateFirstNameByEmpId(@PathVariable("empId") int id, String firstName) {
		return empSer.updateFirstNameByEmpId(id, firstName);
		
	}
	@PutMapping("/update/lastname/{empId}")
	public Employee updateLastNameByEmpId(@PathVariable("empId") int id, String lastName) {
		return empSer.updateLastNameByEmpId(id, lastName);
		
	}
	@PutMapping("/update/hiredateByEmpId/{empId}")
	public Employee updateHireDateByEmpId(@PathVariable("empId") int id,String date) {
		return empSer.updateHireDateByEmpId(id, date);
	}
	@PutMapping("/update/birthdayByEmpId/{empId}")
	public Employee updateBirthDayByEmpId(@PathVariable("empId") int id,String birthdate) {
		return empSer.updateBirthDateByEmpId(id, birthdate);
	}
	 @GetMapping("getAllGenders/gender/{gender}")
	    public ResponseEntity<List<Employee>> getAllEmployeesByGender(@PathVariable Gender gender) {
	        List<Employee> employees = empSer.getAllEmployeesByGender(gender);
	        return ResponseEntity.ok(employees);
	    }
	@GetMapping("getEmployee/empId/{empId}")
	public Employee getEmpById(@PathVariable("empId") int id) {
		return empSer.findEmpByEmpId(id);
	}
	@GetMapping("getEmployee/birthdate/{birthdate}")
	public List<Employee> getByBirthDay(@RequestParam("birthdate") Date date) {
		return empSer.findEmpByBirthDate(date);
	}
	 @DeleteMapping("deleteEmployee/empId/{empno}")
	    public ResponseEntity<String> deleteEmployeeByEmpNo(@PathVariable("empno") int empNo) {
	        String result = empSer.deleteEmployeeByEmpNo(empNo);
	        return ResponseEntity.ok(result);
	    }
	 
	 @GetMapping("getAllEmployee/{noofyear}")
	    public ResponseEntity<List<Employee>> getEmployeesByExperience(@PathVariable("noofyear") int yearsOfExperience) {
	        List<Employee> employees = empSer.getEmployeesByExperience(yearsOfExperience);
	        
	        if (employees.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(employees, HttpStatus.OK);
	        }
	    }
	 
	 	@GetMapping("/getGenders/count/{count}")
	    public long getCountByGender(@PathVariable("count") Employee.Gender gender) {
	        return empSer.getCountByGender(gender);
	    }

	 	 @GetMapping("/getEmployeeCount/count/{noOfYears}")
	     public int getCountOfEmployeesJoinedLastNYears(@PathVariable int noOfYears) {
	         return empSer.getCountOfEmployeesJoinedLastNYears(noOfYears);
	     }
	 	@GetMapping("getAllEmpBy/role/{role}")
	    public List<Employee> getEmployeesByRole(@PathVariable String role) {
	        return empSer.getEmployeesByRole(role);
	    }
		
		@GetMapping("/getAllEmployee/midAge/midageemp")
	    public List<Employee> getMidAgeEmployees() {
	        return empSer.getMidAgeEmployees();
	    }
		
		@GetMapping("/getDistinct/roles")
	    public List<String> findAllDistinctRoles() {
	        return empSer.findAllDistinctRoles();
	    }
		
		@GetMapping("/womenEmployeesCount")
	    public Long countWomenEmployees() {
	        return empSer.countWomenEmployees();
	    }
		@PostMapping
		public Employee addNewEmployee(@RequestBody Employee e) {
			return empSer.newEmployee(e);
		}
		
		
		@GetMapping("getAllEmployees/year/{year}")
		    public List<Employee> listOfEmployeesJoinedAfterYear(@RequestParam int year) {
		        return empSer.listOfEmployeesJoinedAfterYear(year);
		    }
		 
		    @GetMapping("/count/year/{year}")
		    public List<Employee> countEmployeesJoinedAfterYear(@PathVariable ("year") int year) {
		        List<Employee> employees = empSer.countEmployeesJoinedAfterYear(year);
		        empSer.countEmployeesJoinedAfterYear(year);
		        return (employees);
		    }
		    @GetMapping("/gender/{gender}")
		    public List<Employee> getEmployeesByGender(@PathVariable("gender") Gender gender) {
		        List<Employee> employees = empSer.getEmployeesByGender(gender);
		        empSer.getEmployeesByGender(gender);
		        return (employees);
		    }
		 

		 
		    @GetMapping("/manager/fromdate/{fromDate}")
		    public List<Object[]> findManagersAfterDateService(@PathVariable ("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate){
		    	return empSer.findManagersAfterDateService();
		    }
		    
		
}
