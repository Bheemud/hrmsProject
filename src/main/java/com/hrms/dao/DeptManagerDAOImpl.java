package com.hrms.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.hrms.beans.DeptManager;
import com.hrms.beans.DeptManagerId;
import com.hrms.exception.EmployeeDataNotFoundException;
import com.hrms.exception.NoEmployeesFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class DeptManagerDAOImpl implements DepatmentManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DeptManager> getAllDeptManagers() {
        String jpql = "SELECT dm FROM DeptManager dm";
        TypedQuery<DeptManager> query = entityManager.createQuery(jpql, DeptManager.class);
        return query.getResultList();
    }

    @Override
    public DeptManager getDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
        String jpql = "SELECT dm FROM DeptManager dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo";
        TypedQuery<DeptManager> query = entityManager.createQuery(jpql, DeptManager.class);
        query.setParameter("empNo", empNo);
        query.setParameter("deptNo", deptNo);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public List<DeptManager> getDeptManagersByDeptNoAndFromDate(String deptNo, Date fromDate) {
        String jpql = "SELECT dm FROM DeptManager dm WHERE dm.department.deptNo = :deptNo AND dm.fromDate >= :fromDate";
        TypedQuery<DeptManager> query = entityManager.createQuery(jpql, DeptManager.class);
        query.setParameter("deptNo", deptNo);
        query.setParameter("fromDate", fromDate);
        return query.getResultList();
    }

    @Override
    public List<DeptManager> getDeptManagersByEmpNoAndFromDate(int empNo, Date fromDate) {
        String jpql = "SELECT dm FROM DeptManager dm WHERE dm.employee.empNo = :empNo AND dm.fromDate >= :fromDate";
        TypedQuery<DeptManager> query = entityManager.createQuery(jpql, DeptManager.class);
        query.setParameter("empNo", empNo);
        query.setParameter("fromDate", fromDate);
        return query.getResultList();
    }

    @Override
    public List<DeptManager> getDeptManagersByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
        String jpql = "SELECT dm FROM DeptManager dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo AND dm.fromDate >= :fromDate";
        TypedQuery<DeptManager> query = entityManager.createQuery(jpql, DeptManager.class);
        query.setParameter("empNo", empNo);
        query.setParameter("deptNo", deptNo);
        query.setParameter("fromDate", fromDate);
        return query.getResultList();
    }
    
    

    @Override
    public void updateDeptManagerDetails(String empNo, String deptNo, Date newFromDate, Date newToDate) {
        String queryStr = "DELETE FROM DeptManager dm WHERE dm.empNo = :empNo AND dm.deptNo = :deptNo";
        int deletedCount = entityManager.createQuery(queryStr)
                .setParameter("empNo", empNo)
                .setParameter("deptNo", deptNo)
                .executeUpdate();

        if (deletedCount == 0) {
            throw new EmployeeDataNotFoundException("No records found to update");
        }
    }



    @Override
    public void deleteDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
        String queryStr = "DELETE FROM DeptManager dm WHERE dm.empNo = :empNo AND dm.deptNo = :deptNo";
        int deletedCount = entityManager.createQuery(queryStr)
                .setParameter("empNo", empNo)
                .setParameter("deptNo", deptNo)
                .executeUpdate();

        if (deletedCount == 0) {
            throw new NoEmployeesFoundException("No records found to delete");
        }
    }

	@Override
	public void updateDeptManager(String deptNo, String fromDate, DeptManager updatedDeptManagerDetails) {
		 DeptManager existingDeptManager = entityManager.find(DeptManager.class, new DeptManagerId(deptNo));
	        if (existingDeptManager != null) {
	            entityManager.merge(existingDeptManager);
	        } 
}
	    @Transactional
	    @Override
	    public void deleteDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate) {
	        Query query = entityManager.createQuery("DELETE FROM DeptManager d WHERE d.employee.empNo = :empNo AND d.fromDate = :fromDate");
	        query.setParameter("empNo", empNo);
	        query.setParameter("fromDate", fromDate);
	        query.executeUpdate();
	    }
	    @Transactional
	    @Override
	    public void deleteDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate) {
	        Query query = entityManager.createQuery("DELETE FROM DeptManager d WHERE d.department.deptNo = :deptNo AND d.fromDate = :fromDate");
	        query.setParameter("deptNo", deptNo);
	        query.setParameter("fromDate", fromDate);
	        query.executeUpdate();
	    }
	    @Transactional
		@Override
		public void deleteDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
			
			String deleteQuery = "DELETE FROM DeptManager dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate";
	        entityManager.createQuery(deleteQuery)
	            .setParameter("empNo", empNo)
	            .setParameter("deptNo", deptNo)
	            .setParameter("fromDate", fromDate)
	            .executeUpdate();
			
		}
	   
}
