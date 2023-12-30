package com.hrms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.beans.DeptEmployee;
import com.hrms.beans.DeptEmployeeId;
@Repository
public interface DepartmentEmployeeDao extends JpaRepository<DeptEmployee, DeptEmployeeId>{
	
	List<DeptEmployee> findByIdEmpnoAndIdDeptNo(int empno, String deptNo);
    
    List<DeptEmployee> findByDepartmentDeptNoAndFromDate(String deptNo, Date fromDate);
    
    List<DeptEmployee> findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(int empNo, String deptNo, Date fromDate);
    
    List<DeptEmployee> findByEmployee_EmpNoAndFromDate(int empNo, Date fromDate);


    @Modifying
    @Query("DELETE FROM DeptEmployee d WHERE d.employee.empNo = :empNo AND d.fromDate = :fromDate")
    void deleteByEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);
	
	@Modifying
    @Query("DELETE FROM DeptEmployee d WHERE d.department.deptNo = :deptNo AND d.fromDate = :fromDate")
    void deleteByDeptNoAndFromDate(@Param("deptNo") String deptNo, @Param("fromDate") Date fromDate);
	
	@Modifying
    @Query("DELETE FROM DeptEmployee d WHERE d.employee.empNo = :empNo AND d.department.deptNo = :deptNo AND d.fromDate = :fromDate")
    void deleteByEmpNoDeptNoAndFromDate(@Param("empNo") int empNo,@Param("deptNo") String deptNo, @Param("fromDate") Date fromDate);




}
