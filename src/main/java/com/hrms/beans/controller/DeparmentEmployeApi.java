package com.hrms.beans.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.DeptEmployee;
import com.hrms.exception.EmployeeDataNotFoundException;
import com.hrms.service.DepartmentEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping("api/v1/deptemp")
@SecurityRequirement(name = "Bearer Authentication")
public class DeparmentEmployeApi {
	@Autowired
	private DepartmentEmployeeService deptempService;

	@PostMapping
	public DeptEmployee createDeptemp(@RequestBody DeptEmployee deptEmp) {
		return deptempService.createDeptEmp(deptEmp);
	}
	@GetMapping
	public List<DeptEmployee> getAllDept_emp(){
		return deptempService.fetchAllDeptEmp();
	}
	@GetMapping("/empno/{empNo}/deptno/{deptNo}")
	public List<DeptEmployee> searchByEmpnoAndDeptno(@RequestParam int empNo, @RequestParam String deptNo) {
		return deptempService.searchByEmpnoAndDeptno(empNo, deptNo);
	}
	
	@GetMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	public List<DeptEmployee> searchByDeptnoAndFromdate(
	    @PathVariable String deptNo,
	    @PathVariable String fromDate
	) {
	    if (deptNo == null || fromDate == null || deptNo.isEmpty() || fromDate.isEmpty()) {
	        throw new EmployeeDataNotFoundException("deptNo and fromDate cannot be null or empty.");
	    }
	    
	    try {
	        Date fromDateSql = Date.valueOf(fromDate);
	        return deptempService.searchByDeptnoAndFromdate(deptNo, fromDateSql);
	    } catch (IllegalArgumentException e) {
	        throw new EmployeeDataNotFoundException("Invalid date format. Please use the format 'yyyy-MM-dd'.");
	    }
	}
	
	@GetMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
	public List<DeptEmployee> searchByEmpNoDeptNoAndFromDate(
	        @RequestParam int empNo,
	        @RequestParam String deptNo,
	        @PathVariable("fromDate") String fromDate) {
	    if (empNo == 0 || deptNo.isEmpty() || fromDate.isEmpty()) {
	        throw new EmployeeDataNotFoundException("empNo, deptNo, and fromDate cannot be 0 or empty.");
	    }
	    try {
	        Date fromDateSql = Date.valueOf(fromDate);
	        return deptempService.searchByEmpnoDeptNoAndFromDate(empNo, deptNo, fromDateSql);
	    } catch (EmployeeDataNotFoundException e) {
	        throw new EmployeeDataNotFoundException("Invalid date format. Please use the format 'yyyy-MM-dd'.");
	    }
	}
	
	@GetMapping("/empno/{empNo}/fromdate/{fromDate}")
	public List<DeptEmployee> searchByEmpnoAndFromdate(
	        @PathVariable("empNo") int empNo,
	        @PathVariable("fromDate") String fromDate) {
	    try {
	        if (fromDate.trim().isEmpty()) {
	            throw new EmployeeDataNotFoundException("fromDate cannot be null or empty.");
	        }
	        Date fromDateSql = Date.valueOf(fromDate);
	        return deptempService.searchByEmpnoAndFromdate(empNo, fromDateSql);
	    } catch (EmployeeDataNotFoundException e) {
	        throw new EmployeeDataNotFoundException("Invalid input: " + e.getMessage());
	    }
	}
 
 
 
	@PutMapping("/{empno}/{deptno}")
	public DeptEmployee updateDeptEmployee(
	        @PathVariable("empno") int empNo,
	        @PathVariable("deptno") String deptNo,
	        @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String fromDate,
	        @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String toDate) {
 
	    LocalDate fromDateLocal = LocalDate.parse(fromDate);
	    LocalDate toDateLocal = LocalDate.parse(toDate);
	    Date fromDateSql = Date.valueOf(fromDateLocal);
	    Date toDateSql = Date.valueOf(toDateLocal);
 
	    return deptempService.updateDeptEmployee(deptNo, empNo, fromDateSql, toDateSql);
	}
 
@DeleteMapping("empno/{empno}/deptno/{deptno}")
public String deleteDeptEmp(@PathVariable("empno")int empNo,@PathVariable("deptno")String deptNo) {
	deptempService.deleteDeptEmp(deptNo, empNo);
	return "DeptEmp deleted successfully";
}
 
@Transactional
@DeleteMapping("/empno/{empno}/fromdate/{fromdate}")
public ResponseEntity<String> deleteDeptEmpByEmpNoAndFromDate(
        @PathVariable("empno") int empNo,
        @PathVariable("fromdate") String fromDate) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedFromDate = new java.sql.Date(dateFormat.parse(fromDate).getTime());
        deptempService.deleteByEmpNoAndFromDate(empNo, convertedFromDate);
        return ResponseEntity.ok("DeptEmp deleted successfully");
    } catch (ParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid date format. Please use the format 'yyyy-MM-dd'.");
    }
}
 
@Transactional
@DeleteMapping("/deptno/{deptno}/fromdate/{fromdate}")
public ResponseEntity<String> deleteDeptDeptByEmpNoAndFromDate(
        @PathVariable("deptno") String deptNo,
        @PathVariable("fromdate") String fromDate) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedFromDate = new java.sql.Date(dateFormat.parse(fromDate).getTime());
        deptempService.deleteByDeptNoAndFromDate(deptNo, convertedFromDate);
        return ResponseEntity.ok("DeptEmp deleted successfully");
    } catch (ParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid date format. Please use the format 'yyyy-MM-dd'.");
    }
}
 
@Transactional
@DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
public ResponseEntity<String> deleteDeptManagerEmpNoAndByDeptNoAndFromDate(
        @PathVariable("empNo") int empNo,
        @PathVariable("deptNo") String deptNo,
        @PathVariable("fromDate") String fromDate) {
    try {
        Date fromDateSql = Date.valueOf(fromDate);
        deptempService.deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDateSql);
        return ResponseEntity.ok("DeptManager deleted successfully");
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid date format. Please use the format 'yyyy-MM-dd'.");
    }
}
 
	
	
}
