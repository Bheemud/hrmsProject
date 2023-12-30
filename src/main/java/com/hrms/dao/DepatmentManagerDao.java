package com.hrms.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.hrms.beans.DeptManager;
@Repository
public interface DepatmentManagerDao {
	

List<DeptManager> getAllDeptManagers();
    
    DeptManager getDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo);
    
    List<DeptManager> getDeptManagersByDeptNoAndFromDate(String deptNo, Date fromDate);
    
    List<DeptManager> getDeptManagersByEmpNoAndFromDate(int empNo, Date fromDate);
    
    List<DeptManager> getDeptManagersByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate);
    void deleteDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo);

	void updateDeptManagerDetails(String empNo, String deptNo, Date newFromDate, Date newToDate);
	void updateDeptManager(String deptNo, String fromDate, DeptManager updatedDeptManagerDetails);

    void deleteDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate);
    void deleteDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate);

    void deleteDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate);




}
