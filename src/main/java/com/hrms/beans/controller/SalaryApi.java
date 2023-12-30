package com.hrms.beans.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.Salary;
import com.hrms.service.SalaryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/salaries")
@SecurityRequirement(name = "Bearer Authentication")
public class SalaryApi {

	@Autowired
	private SalaryService salaryService;


	@PutMapping("/update/salary/{date}")
	public List<Salary> updateSalaryFromDate(@PathVariable("date") String date, int sal) {
		return salaryService.updateSalaryByFromDate(date, sal);

	}

	

	@GetMapping
	public List<Salary> fetchAllSalaries() {
		return salaryService.fetchAllSalaries();

	}

	@PostMapping
	public Salary addNewSalary(@RequestBody Salary s) {
		return salaryService.addNewSalary(s);
	}

	@GetMapping("/empno/{empNo}")
	public List<Salary> fetchAllSalaryByEmpNo(@PathVariable("empNo") String empNo) {
		return salaryService.fechAllSalary(empNo);
	}


	@DeleteMapping("/empno/{empNo}")
	public List<Salary> deleteSalaryByEmpNo(@PathVariable("empNo") String empNo) {
		List<Salary> deletedSalaries = salaryService.deleteSalaryByEmpNum(empNo);
		salaryService.deleteSalaryByEmpNum(empNo);
		return deletedSalaries;
	}


	@PutMapping("/fromdate/{fromdate}/salary/{salary}")
	public List<Salary> updateSalaryByFromDate(
	        @RequestParam("fromdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate,
	        @RequestParam("salary") int salary) {
	    return salaryService.updateSalaryByFromDate(fromdate, salary);
	}

	@GetMapping("/fromdate/{fromDate}")
	public List<Salary> fetchAllSalaryByFromDate(
	        @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	    return salaryService.fetchAllSalaryByFromDate(fromDate);
	}


	@GetMapping("/empno/{empNo}/fromdate/{fromDate}/")
	public List<Salary> searchSalaryByEmpNoAndFromDate(@PathVariable("empNo") String empNo,
	        @PathVariable("fromDate") String fromDate) {
	    Date parsedDate;
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        parsedDate = dateFormat.parse(fromDate);
	    } catch (ParseException e) {
	        return Collections.emptyList();
	    }
	    return salaryService.searchSalariesByFromDate(empNo, parsedDate);
	}



	@DeleteMapping("/fromdate{fromDate}")
	public List<Salary> deleteSalaryByFromDate(
			@PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
		List<Salary> deletedSalaries = salaryService.deleteSalaryByFromDate(fromDate);
		salaryService.deleteSalaryByFromDate(fromDate);
		return deletedSalaries;
	}


	
	@PutMapping("/empno/{empNo}")
	public List<Salary> updateSalaryByEmpNo(@PathVariable("empNo") String empNo, @RequestParam("salary") int salary) {
	    return salaryService.updateSalaryByEmpNo(empNo, salary);
	}


	@PutMapping("/empno/{empNo}/fromdate/{fromDate}")
	public List<Salary> updateSalaryByEmpNo(@PathVariable("empNo") String empNo,
	        @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	    return salaryService.deleteSalaryByEmpNoAndFromDate(empNo, fromDate);
	}


	@GetMapping("/salary")
	public List<Salary> fetchAllSalary(@RequestParam("minsalary") int minSalary,
			@RequestParam("maxsalary") int maxSalary) {
		return salaryService.fetchAllSalary(minSalary, maxSalary);
	}

	@DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
	public List<Salary> deleteSalaryByEmpNoAndFromDate(@RequestParam String empNo,
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	    return salaryService.deleteSalaryByEmpNoAndFromDate(empNo, fromDate);
	}

}
