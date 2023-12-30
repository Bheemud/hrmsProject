package com.hrms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.beans.Salary;

@Repository
public interface SalaryDao extends JpaRepository<Salary, Integer> {


	@Query("SELECT s FROM Salary s WHERE s.empNo = :empNo AND s.fromDate = :fromDate")
	List<Salary> findByEmpNoAndFromDate(@Param("empNo") String empNo, @Param("fromDate") Date fromDate);

	public List<Salary> findByEmpNo(String empNo);

	public List<Salary> deleteByEmpNo(String empNo);

	public List<Salary> findByFromDate(Date fromDate);

	public List<Salary> deleteByEmpNoAndFromDate(String empNo, Date fromDate);
	
	@Query("SELECT s FROM Salary s WHERE s.salary BETWEEN :minSalary AND :maxSalary")
	List<Salary> findSalariesInRange(@Param("minSalary") int minSalary, @Param("maxSalary") int maxSalary);

	void deleteByFromDate(Date fromDate);

}
