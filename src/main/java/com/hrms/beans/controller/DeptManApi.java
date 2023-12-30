package com.hrms.beans.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.DeptManager;
import com.hrms.service.DeptManServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/v1/deptmanager")
@SecurityRequirement(name = "Bearer Authentication")
public class DeptManApi {
	
	@Autowired
	private DeptManServiceImpl deptManagerService;
	
	 @GetMapping("/")
	    public List<DeptManager> getAllDeptManagers() {
	        return deptManagerService.getAllDeptManagers();
	    }

	    @GetMapping("/empno/{empno}/deptno/{deptno}")
	    public DeptManager getDeptManagerByEmpNoAndDeptNo(
	            @PathVariable("empno") int empNo,
	            @PathVariable("deptno") String deptNo
	    ) {
	        return deptManagerService.getDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
	    }

	    @GetMapping("/deptno/{deptno}/fromdate/{fromdate}")
	    public List<DeptManager> getDeptManagersByDeptNoAndFromDate(
	            @PathVariable("deptno") String deptNo,
	            @PathVariable("fromdate")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date fromDate
	    ) {
	        return deptManagerService.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate);
	    }

	    @GetMapping("/empno/{empno}/fromdate/{fromDate}")
	    public List<DeptManager> getDeptManagersByEmpNoAndFromDate(
	            @PathVariable("empno") int empNo,
	            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate
	    ) {
	        return deptManagerService.getDeptManagersByEmpNoAndFromDate(empNo, fromDate);
	    }

	    @GetMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromDate}")
	    public List<DeptManager> getDeptManagersByEmpNoDeptNoAndFromDate(
	            @PathVariable("empno") int empNo,
	            @PathVariable("deptno") String deptNo,
	            @PathVariable("fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate
	    ) {
	        return deptManagerService.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
	    }
	    @DeleteMapping("/empno/{empno}/deptno/{deptno}")
	    public String deleteDeptManagerByEmpNoAndDeptNo(
	            @PathVariable("empno") int empNo,
	            @PathVariable("deptno") String deptNo) {
	        deptManagerService.deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
	        return "DeptManager Deleted Successfully";
	    }

	    @PutMapping("/{empno}/{deptno}")
	    public String updateDeptManagerDetails(
	            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
	            @PathVariable String empno,
	            @PathVariable String deptno) {
	    	deptManagerService.updateDeptManagerDetails(empno, deptno, fromDate, toDate);
	        return "Employee assigned to department updated Successfully";
	    }
	    
	    
	    @PutMapping("/deptno/{deptno}/fromdate/{fromdate}")
	    public ResponseEntity<String> updateDeptManagerDetails(
	            @PathVariable("deptno") String deptNo,
	            @PathVariable("fromdate") String fromDate,
	            @RequestBody DeptManager updatedDeptManagerDetails) {
	        
	    	deptManagerService.updateDeptManager(deptNo, fromDate, updatedDeptManagerDetails);
	 
	        return ResponseEntity.ok("Department manager details updated successfully.");
	    }
	    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
	    public ResponseEntity<String> deleteDeptManagerByEmpNoAndFromDate(
	            @PathVariable("empNo") int empNo,
	            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	        deptManagerService.deleteDeptManagerByEmpNoAndFromDate(empNo, fromDate);
	        return ResponseEntity.ok("DeptManager deleted successfully");
	    }
	    @DeleteMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	    public ResponseEntity<String> deleteDeptManagerByDeptNoAndFromDate(
	            @PathVariable("deptNo") String deptNo,
	            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	        deptManagerService.deleteDeptManagerByDeptNoAndFromDate(deptNo, fromDate);
	        return ResponseEntity.ok("DeptManager Deleted successfully");
	    }
	    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
	    public ResponseEntity<String> deleteDeptManagerByEmpNoDeptNoAndFromDate(
	            @PathVariable("empNo") int empNo,
	            @PathVariable("deptNo") String deptNo,
	            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
	        deptManagerService.deleteDeptManagerByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
	        return ResponseEntity.ok("DeptManager deleted successfully");
	    }
	    
}
