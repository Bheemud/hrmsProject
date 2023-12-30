package com.hrms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.beans.DeptManager;
import com.hrms.dao.DepatmentManagerDao;

import jakarta.transaction.Transactional;

@Component
public class DeptManServiceImpl implements DeptManService {

	@Autowired
	private DepatmentManagerDao deptManagerDao;

	@Override
	public List<DeptManager> getAllDeptManagers() {
		return deptManagerDao.getAllDeptManagers();
	}

	@Override
	public DeptManager getDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
		return deptManagerDao.getDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
	}

	@Override
	public List<DeptManager> getDeptManagersByDeptNoAndFromDate(String deptNo, Date fromDate) {
		return deptManagerDao.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate);
	}

	@Override
	public List<DeptManager> getDeptManagersByEmpNoAndFromDate(int empNo, Date fromDate) {
		return deptManagerDao.getDeptManagersByEmpNoAndFromDate(empNo, fromDate);
	}

	@Override
	public List<DeptManager> getDeptManagersByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
		return deptManagerDao.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
	}

	@Override
	public void deleteDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
		deptManagerDao.deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
	}

	@Override
	@Transactional
	public void updateDeptManagerDetails(String empNo, String deptNo, Date newFromDate, Date newToDate) {
		deptManagerDao.updateDeptManagerDetails(empNo, deptNo, newFromDate, newToDate);
	}

	@Override
	public void updateDeptManager(String deptNo, String fromDate, DeptManager updatedDeptManagerDetails) {

		deptManagerDao.updateDeptManager(deptNo, fromDate, updatedDeptManagerDetails);
	}
	@Override
    public void deleteDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate) {
		deptManagerDao.deleteDeptManagerByEmpNoAndFromDate(empNo, fromDate);
    }
	@Override
    public void deleteDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate) {
		deptManagerDao.deleteDeptManagerByDeptNoAndFromDate(deptNo, fromDate);
    }
	
	 @Override
	    public void deleteDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
	        deptManagerDao.deleteDeptManagerByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
	    }

}
