package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.beans.DeptEmployee;

public interface DepartmentEmployeeService {

	DeptEmployee createDeptEmp(DeptEmployee dept_emp);

	
	List<DeptEmployee> fetchAllDeptEmp();

	List<DeptEmployee> searchByEmpnoAndDeptno(int empno, String deptno);

	List<DeptEmployee> searchByEmpnoDeptNoAndFromDate(int empNo, String deptNo, Date from_date);

	List<DeptEmployee> searchByEmpnoAndFromdate(int empNo, Date fromDate);
	
	List<DeptEmployee> searchByDeptnoAndFromdate(String deptNo, Date fromDate);

	
	DeptEmployee updateDeptEmployee(String deptNo, int empNo, java.util.Date newFromDate, java.util.Date newToDate);

	
	String deleteDeptEmp(String deptNo, int empNo);

	void deleteByEmpNoAndFromDate(int empNo, Date fromDate);

	void deleteByDeptNoAndFromDate(String deptNo, Date fromDate);

	void deleteByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

	

}
