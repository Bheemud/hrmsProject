package com.hrms.service;

import java.util.Date;
import java.util.List;

import com.hrms.beans.Salary;

public interface SalaryService {

	public List<Salary> fetchAllSalaries();
	public List<Salary> updateSalaryByFromDate(String date,int salary);
	public Salary addNewSalary(Salary s);
	public List<Salary> fechAllSalary(String empNo);
	public List<Salary> deleteSalaryByEmpNum(String empNo);
	List<Salary> deleteSalaryByEmpno(String empNo, Date fromDate);
    List<Salary> fetchAllSalaryByFromDate(Date fromDate);
	List<Salary> searchSalariesByFromDate(String empNo, Date fromDate);
	List<Salary> deleteSalaryByEmpNoAndFromDate(String empNo, Date fromDate);
	List<Salary> updateSalaryByFromDate(Date fromdate, int salary);
	List<Salary> deleteSalaryByFromDate(Date fromDate);
	List<Salary> updateSalaryByEmpNo(String empNo,int salary);
	List<Salary> updateSalaryByEmpNo(String empNo,Date fromDate);
	public List<Salary> fetchAllSalary(int minSalary, int maxSalary);
 

}
