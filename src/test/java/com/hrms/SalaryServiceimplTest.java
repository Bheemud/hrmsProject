package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.beans.Salary;
import com.hrms.dao.SalaryDao;
import com.hrms.exception.SalaryServiceException;
import com.hrms.service.SalaryServiceImpl;
 
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SalaryServiceimplTest {
 
    @Mock
    private SalaryDao salaryDao;
 
    @InjectMocks
    private SalaryServiceImpl salaryService;
 
    @BeforeEach
    public void setUp() {
    	 MockitoAnnotations.initMocks(this);
    }
 
    @Test
     void testFetchAllSalaries() throws SalaryServiceException {
        
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findAll()).thenReturn(mockSalaries);
 
     
        List<Salary> result = salaryService.fetchAllSalaries();
 
        
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testAddNewSalary() throws SalaryServiceException {
        
        Salary mockSalary = new Salary();
        when(salaryDao.save(any(Salary.class))).thenReturn(mockSalary);
 
        
        Salary result = salaryService.addNewSalary(mockSalary);
 
        
        assertEquals(mockSalary, result);
    }
 
    @Test
     void testFechAllSalary() throws SalaryServiceException {
        
        String empNo = "EMP001";
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findByEmpNo(empNo)).thenReturn(mockSalaries);
 
       
        List<Salary> result = salaryService.fechAllSalary(empNo);
 
       
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByEmpNo() throws SalaryServiceException {
       
        String empNo = "EMP001";
        List<Salary> mockDeletedSalaries = new ArrayList<>();
        when(salaryDao.findByEmpNo(empNo)).thenReturn(mockDeletedSalaries);
 
        
        List<Salary> result = salaryService.deleteSalaryByEmpNum(empNo);
 
        
        assertEquals(mockDeletedSalaries, result);
        verify(salaryDao, times(1)).deleteByEmpNo(empNo);
    }
 
    @Test
     void testUpdateSalaryByFromDate() throws SalaryServiceException {
       
        Date fromDate = new Date();
        int salary = 50000;
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findByFromDate(any())).thenReturn(mockSalaries);
 
       
        List<Salary> result = salaryService.updateSalaryByFromDate(fromDate, salary);
 
      
        assertEquals(mockSalaries, result);
        verify(salaryDao, times(mockSalaries.size())).save(any());
    }
 
    @Test
     void testUpdateSalaryByEmpNo() throws SalaryServiceException {
        
        String empNo = "EMP001";
        int salary = 60000;
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findByEmpNo(empNo)).thenReturn(mockSalaries);
 
      
        List<Salary> result = salaryService.updateSalaryByEmpNo(empNo, salary);
 
        
        assertEquals(mockSalaries, result);
        verify(salaryDao, times(mockSalaries.size())).save(any());
    }
 
    @Test
     void testFetchAllSalaryByFromDate() throws SalaryServiceException {
       
        Date fromDate = new Date();
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findByFromDate(fromDate)).thenReturn(mockSalaries);
 
      
        List<Salary> result = salaryService.fetchAllSalaryByFromDate(fromDate);
 
       
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testSearchSalariesByFromDate() throws SalaryServiceException {
        
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockSalaries);
 
       
        List<Salary> result = salaryService.searchSalariesByFromDate(empNo, fromDate);
 
        
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByEmpno() throws SalaryServiceException {
       
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockDeletedSalaries = new ArrayList<>();
        when(salaryDao.findByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockDeletedSalaries);
 
     
        List<Salary> result = salaryService.deleteSalaryByEmpno(empNo, fromDate);
 
       
        assertEquals(mockDeletedSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByFromDate() throws SalaryServiceException {
        
        Date fromDate = new Date();
        List<Salary> mockSalaries = new ArrayList<>();
//        when(salaryDao.findAll()).thenReturn(mockSalaries);
 
        List<Salary> result = salaryService.deleteSalaryByFromDate(fromDate);
              assertEquals(new ArrayList<Salary>(), result);
    }
 
    @Test
     void testUpdateSalaryByEmpNoAndFromDate() throws SalaryServiceException {
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockUpdatedSalaries = new ArrayList<>();
//        when(salaryDao.findByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockUpdatedSalaries);
        List<Salary> result = salaryService.updateSalaryByEmpNo(empNo, fromDate);
        assertEquals(mockUpdatedSalaries, result);
    }
 
    @Test
     void testFetchAllSalaryInRange() {
        int minSalary = 50000;
        int maxSalary = 80000;
        List<Salary> mockSalaries = new ArrayList<>();
        when(salaryDao.findSalariesInRange(minSalary, maxSalary)).thenReturn(mockSalaries);
 
        List<Salary> result = salaryService.fetchAllSalary(minSalary, maxSalary);
 
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByEmpNoAndFromDate() {
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockDeletedSalaries = new ArrayList<>();
        when(salaryDao.deleteByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockDeletedSalaries);
 
        List<Salary> result = salaryService.deleteSalaryByEmpNoAndFromDate(empNo, fromDate);
 
        assertEquals(mockDeletedSalaries, result);
    }
}