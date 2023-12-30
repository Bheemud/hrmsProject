package com.hrms.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.hrms.beans.Departments;
import com.hrms.beans.DeptEmployee;
import com.hrms.beans.DeptEmployeeId;
import com.hrms.dao.DepartmentEmployeeDao;
import com.hrms.exception.EmployeeDataNotFoundException;

import jakarta.transaction.Transactional;

@Component
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService{
	
	@Autowired
	private DepartmentEmployeeDao deptEmpDao;
//	@Autowired
@SuppressWarnings("unused")
	//	private DepartmentEmployeeService departmentEmployeeService;
//	
	@Override
	public DeptEmployee createDeptEmp(DeptEmployee deptEmp) {
	    String deptNo = deptEmp.getId().getDeptNo();

	    Date currentDate = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    String currentDateStr = dateFormat.format(currentDate);
	   
	    if(deptEmp == null) {
	    	throw new EmployeeDataNotFoundException("Department employee is null");
	    }else {
	    	try {
		    	
		        Date parsedDate = dateFormat.parse(currentDateStr);
		        deptEmp.setFromDate(parsedDate);
		        deptEmp.setToDate(parsedDate);
		        return deptEmpDao.save(deptEmp);
		    } catch (ParseException | java.text.ParseException e) {
		        throw new RuntimeException("Error parsing date", e);
		    }
	    }
	    	
	   
	}


	@Override //
	public List<DeptEmployee> searchByEmpnoAndDeptno(int empno, String deptNo) {
	    if (empno == 0) {
	        throw new EmployeeDataNotFoundException("empno cannot be 0");
	    }
	    if (deptNo == null || deptNo.trim().isEmpty() || "0".equals(deptNo.trim())) {
	        throw new EmployeeDataNotFoundException("deptNo cannot be null, empty, or 0");
	    }

	    List<DeptEmployee> result = deptEmpDao.findByIdEmpnoAndIdDeptNo(empno, deptNo);

	    if (result.isEmpty()) {
	        throw new EmployeeDataNotFoundException("No department employees found for empno: " + empno + " and deptNo: " + deptNo);
	    }

	    return result;
	}


	@Override //
	public String deleteDeptEmp(String deptNo, int empNo) {
	    if (deptNo == null || deptNo.trim().isEmpty() || "0".equals(deptNo.trim())) {
	        throw new EmployeeDataNotFoundException("DeptNo cannot be null, empty, or 0");
	    }

	    if (empNo == 0) {
	        throw new EmployeeDataNotFoundException("EmpNo cannot be 0");
	    }

	    Optional<DeptEmployee> id = deptEmpDao.findById(new DeptEmployeeId(deptNo, empNo));
	    if (id.isPresent()) {
	        deptEmpDao.delete(id.get());
	        return "DeptEmployee deleted successfully";
	    } else {
	        return "No such DeptEmployee in the database";
	    }
	}


	@Override 
	public List<DeptEmployee> searchByDeptnoAndFromdate(String deptNo, java.sql.Date fromDate) {
				List<DeptEmployee> result = deptEmpDao.findByDepartmentDeptNoAndFromDate(deptNo, fromDate);
				if(result.isEmpty()) {
					throw new EmployeeDataNotFoundException("No data found for deptno: "+deptNo+" and for fromDate: "+fromDate);
				}
				return result;
	}
 
	@Override 
	public List<DeptEmployee> searchByEmpnoDeptNoAndFromDate(int empNo, String deptNo, java.sql.Date fromdate) {
		List<DeptEmployee> result = deptEmpDao.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromdate);
		if(result.isEmpty()) {
			throw new EmployeeDataNotFoundException("No data found for empno: "+empNo+" , deptno: "+ deptNo +" and for fromDate: "+fromdate);
		}
		return result;
	}
	 @Override
	    public List<DeptEmployee> searchByEmpnoAndFromdate(int empNo, java.sql.Date fromDate) {
	        List<DeptEmployee> result = deptEmpDao.findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	        if (result.isEmpty()) {
	            throw new EmployeeDataNotFoundException("No data found for empno: " + empNo + " and fromDate: " + fromDate);
	        }
	        return result;
	    }
 
	@Override
	@Transactional
	public DeptEmployee updateDeptEmployee(String deptNo, int empNo, Date newFromDate, Date newToDate) {
	    DeptEmployeeId deptEmployeeId = new DeptEmployeeId(deptNo, empNo);
	    Optional<DeptEmployee> optionalDeptEmployee = deptEmpDao.findById(deptEmployeeId);
 
	    if (optionalDeptEmployee.isPresent()) {
	        DeptEmployee existingDeptEmployee = optionalDeptEmployee.get();
	        existingDeptEmployee.setFromDate(newFromDate);
	        existingDeptEmployee.setToDate(newToDate);
	        return deptEmpDao.save(existingDeptEmployee);
	    } else {
	        throw new EmployeeDataNotFoundException("DeptEmployee not found for deptNo: " + deptNo + " and empNo: " + empNo);
	    }
	}

 
	@Override 
	public List<DeptEmployee> fetchAllDeptEmp() {
		 List<DeptEmployee> deptEmployees;
		    try {
		        deptEmployees = deptEmpDao.findAll();
		    } catch (DataAccessException e) {
		        e.printStackTrace();
		        throw new EmployeeDataNotFoundException("Error fetching department employees");
		    }
		    return deptEmployees;
	}
 
	
	@Transactional
	@Override 
	public void deleteByEmpNoAndFromDate(int empNo, java.sql.Date fromDate) {
		try {
	        deptEmpDao.deleteByEmpNoAndFromDate(empNo, fromDate);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
 
	    	throw new EmployeeDataNotFoundException("DeptEmployee not found for fromDate: " + fromDate + " and empNo: " + empNo);
	    }
 
	}
	@Transactional
	@Override 
	public void deleteByDeptNoAndFromDate(String deptNo, java.sql.Date fromDate) {
		try {
		deptEmpDao.deleteByDeptNoAndFromDate(deptNo, fromDate);
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new EmployeeDataNotFoundException("DeptEmployee not found for deptNo: " + deptNo + " and fromDate: " + fromDate);
		}
	}
 
	@Override
	@Transactional
	public void deleteByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo, java.sql.Date fromDate) {
	    try {
	        deptEmpDao.deleteByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        throw new EmployeeDataNotFoundException("DeptEmployee not found for empNo: " + empNo + " and deptNo: " + deptNo + " and fromDate: " + fromDate);
	    }
	}
 
	
 
 
	
}


	

