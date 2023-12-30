package com.hrms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.beans.Salary;
import com.hrms.dao.SalaryDao;
import com.hrms.exception.SalaryServiceException;

import jakarta.transaction.Transactional;

@Component
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryDao salaryDao;



	@Override
	public List<Salary> updateSalaryByFromDate(String date, int salary) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate;
        try {
            fromDate = dateFormat.parse(date);
        } catch (ParseException e) {
            return Collections.emptyList();
        }

        List<Salary> list = salaryDao.findByFromDate(fromDate);
        for (Salary salaryToUpdate : list) {
            salaryToUpdate.setSalary(salary);
            salaryDao.save(salaryToUpdate);
        }

        return salaryDao.findByFromDate(fromDate);    }


	@Override
	public List<Salary> fetchAllSalaries() throws SalaryServiceException {
	    return salaryDao.findAll();
	}

	@Override
	public Salary addNewSalary(Salary salary) throws SalaryServiceException {
	    return salaryDao.save(salary);
	}
	@Override
	public List<Salary> fechAllSalary(String empNo) throws SalaryServiceException {
	    return salaryDao.findByEmpNo(empNo);
	}

	@Override
	@Transactional
	 public List<Salary> deleteSalaryByEmpNum(String empNo) throws SalaryServiceException {
        try {
            List<Salary> deletedSalaries = salaryDao.findByEmpNo(empNo);
            salaryDao.deleteByEmpNo(empNo);
            return deletedSalaries;
        } catch (Exception e) {
            throw new SalaryServiceException("Error deleting salaries by empNo: " + empNo, e);
        }
    }

	@Override
	public List<Salary> updateSalaryByFromDate(Date fromdate, int salary) throws SalaryServiceException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate;
        try {
            fromDate = dateFormat.parse(dateFormat.format(fromdate));
        } catch (ParseException e) {
            throw new SalaryServiceException("Error parsing the fromDate", e);
        }

        List<Salary> list = salaryDao.findByFromDate(fromDate);
        for (Salary salaryToUpdate : list) {
            salaryToUpdate.setSalary(salary);
            salaryDao.save(salaryToUpdate);
        }
        return list;
    }
	@Override
	 public List<Salary> updateSalaryByEmpNo(String empNo, int salary) throws SalaryServiceException {
        try {
            List<Salary> list = salaryDao.findByEmpNo(empNo);
            for (Salary salaryToUpdate : list) {
                salaryToUpdate.setSalary(salary);
                salaryDao.save(salaryToUpdate);
            }
            return list;
        } catch (Exception e) {
            throw new SalaryServiceException("Error updating salaries by empNo", e);
        }
    }
	@Override
	public List<Salary> fetchAllSalaryByFromDate(Date fromDate) throws SalaryServiceException {
	    try {
	        return salaryDao.findByFromDate(fromDate);
	    } catch (Exception e) {
	        throw new SalaryServiceException("Error fetching salaries by fromDate from the database", e);
	    }
	}

	@Override
	public List<Salary> searchSalariesByFromDate(String empNo, Date fromDate) throws SalaryServiceException {
	    try {
	        return salaryDao.findByEmpNoAndFromDate(empNo, fromDate);
	    } catch (Exception e) {
	        throw new SalaryServiceException("Error searching salaries by empNo and fromDate from the database", e);
	    }
	}


	 @Override
	 public List<Salary> deleteSalaryByEmpno(String empNo, Date fromDate) throws SalaryServiceException {
	        try {
	            List<Salary> deletedSalaries = salaryDao.findByEmpNoAndFromDate(empNo, fromDate);
	            salaryDao.findByEmpNoAndFromDate(empNo, fromDate);
	            return deletedSalaries;
	        } catch (Exception e) {
	            throw new SalaryServiceException("Error deleting salaries by empNo and fromDate from the database", e);
	        }
	    }

	 @Override
	 public List<Salary> deleteSalaryByFromDate(Date fromDate) throws SalaryServiceException {
	        try {
	            List<Salary> salaries = new ArrayList<>(); // Initialize the list
	            Iterator<Salary> iterator = salaries.iterator();
	            List<Salary> deletedSalaries = new ArrayList<>();

	            while (iterator.hasNext()) {
	                Salary salary = iterator.next();
	                if (salary.getFromDate().equals(fromDate)) {
	                    iterator.remove();
	                    deletedSalaries.add(salary);
	                }
	            }
	            return deletedSalaries;
	        } catch (Exception e) {
	            throw new SalaryServiceException("Error deleting salaries by fromDate", e);
	        }
	    }

	 @Override
	 public List<Salary> updateSalaryByEmpNo(String empNo, Date fromDate) throws SalaryServiceException {
	        try {
	            List<Salary> updatedSalaries = new ArrayList<>();
	            List<Salary> salaries = new ArrayList<>(); // Assuming salaries is initialized somewhere

	            for (Salary salary : salaries) {
	                if (salary.getEmpNo().equals(empNo) && salary.getFromDate().equals(fromDate)) {
	                    // Assuming your Salary class has a copy constructor
	                    updatedSalaries.add(new Salary());
	                }
	            }
	            return updatedSalaries;
	        } catch (Exception e) {
	            throw new SalaryServiceException("Error updating salaries by empNo and fromDate", e);
	        }
	    }

	@Override
	public List<Salary> fetchAllSalary(int minSalary, int maxSalary) {
		return salaryDao.findSalariesInRange(minSalary, maxSalary);
	}
	 @Override
	    @Transactional
	    public List<Salary> deleteSalaryByEmpNoAndFromDate(String empNo, Date fromDate) {
		 return salaryDao.deleteByEmpNoAndFromDate(empNo, fromDate);
	    }

	
	



}
