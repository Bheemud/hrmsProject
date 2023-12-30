package com.hrms;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.beans.Salary;
import com.hrms.beans.controller.SalaryApi;
import com.hrms.service.SalaryService;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
@SpringBootTest
 class SalaryApiTest {
 
    @Mock
    private SalaryService salaryService;
 
    @InjectMocks
    private SalaryApi salaryApi;
 
    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
     void testFetchAllSalaries() {
        List<Salary> mockSalaries = Collections.emptyList();
        when(salaryService.fetchAllSalaries()).thenReturn(mockSalaries);
        List<Salary> result = salaryApi.fetchAllSalaries();
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testAddNewSalary() {
        Salary mockSalary = new Salary();
        when(salaryService.addNewSalary(any(Salary.class))).thenReturn(mockSalary);
        Salary result = salaryApi.addNewSalary(mockSalary);
        assertEquals(mockSalary, result);
    }
 
    @Test
     void testFetchAllSalaryByEmpNo() {
        String empNo = "EMP001";
        List<Salary> mockSalaries = Collections.emptyList();
        when(salaryService.fechAllSalary(empNo)).thenReturn(mockSalaries);
        List<Salary> result = salaryApi.fetchAllSalaryByEmpNo(empNo);
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByEmpNo() {
        String empNo = "EMP001";
        List<Salary> mockDeletedSalaries = Collections.emptyList();
        when(salaryService.deleteSalaryByEmpNum(empNo)).thenReturn(mockDeletedSalaries);
        List<Salary> result = salaryApi.deleteSalaryByEmpNo(empNo);
        assertEquals(mockDeletedSalaries, result);
        verify(salaryService, times(1)).deleteSalaryByEmpNum(empNo);
    }
 
    @Test
     void testUpdateSalaryByFromDate() {
        Date fromDate = new Date();
        int salary = 50000;
        List<Salary> mockUpdatedSalaries = Collections.emptyList();
        when(salaryService.updateSalaryByFromDate(fromDate, salary)).thenReturn(mockUpdatedSalaries);
        List<Salary> result = salaryApi.updateSalaryByFromDate(fromDate, salary);
        assertEquals(mockUpdatedSalaries, result);
    }
 
    @Test
     void testFetchAllSalaryByFromDate() {
        Date fromDate = new Date();
        List<Salary> mockSalaries = Collections.emptyList();
        when(salaryService.fetchAllSalaryByFromDate(fromDate)).thenReturn(mockSalaries);
        List<Salary> result = salaryApi.fetchAllSalaryByFromDate(fromDate);
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testSearchSalaryByEmpNoAndFromDate() {
        String empNo = "EMP001";
        String fromDate = "01-01-2023";
        Date parsedDate;
        try {
            parsedDate = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
            parsedDate = null;
        }
        List<Salary> mockSalaries = Collections.emptyList();
        when(salaryService.searchSalariesByFromDate(empNo, parsedDate)).thenReturn(mockSalaries);
        List<Salary> result = salaryApi.searchSalaryByEmpNoAndFromDate(empNo, fromDate);
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByFromDate() {
        Date fromDate = new Date();
        List<Salary> mockDeletedSalaries = Collections.emptyList();
        when(salaryService.deleteSalaryByFromDate(fromDate)).thenReturn(mockDeletedSalaries);
        List<Salary> result = salaryApi.deleteSalaryByFromDate(fromDate);
        assertEquals(mockDeletedSalaries, result);
        verify(salaryService, times(1)).deleteSalaryByFromDate(fromDate);
    }
 
    @Test
     void testUpdateSalaryByEmpNo() {
        String empNo = "EMP001";
        int salary = 60000;
        List<Salary> mockUpdatedSalaries = Collections.emptyList();
        when(salaryService.updateSalaryByEmpNo(empNo, salary)).thenReturn(mockUpdatedSalaries);
        List<Salary> result = salaryApi.updateSalaryByEmpNo(empNo, salary);
        assertEquals(mockUpdatedSalaries, result);
    }
 
    @Test
     void testUpdateSalaryByEmpNoAndFromDate() {
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockUpdatedSalaries = Collections.emptyList();
        when(salaryService.deleteSalaryByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockUpdatedSalaries);
        List<Salary> result = salaryApi.updateSalaryByEmpNo(empNo, fromDate);
        assertEquals(mockUpdatedSalaries, result);
    }
 
    @Test
     void testFetchAllSalary() {
        int minSalary = 50000;
        int maxSalary = 80000;
        List<Salary> mockSalaries = Collections.emptyList();
        when(salaryService.fetchAllSalary(minSalary, maxSalary)).thenReturn(mockSalaries);
        List<Salary> result = salaryApi.fetchAllSalary(minSalary, maxSalary);
        assertEquals(mockSalaries, result);
    }
 
    @Test
     void testDeleteSalaryByEmpNoAndFromDate() {
        String empNo = "EMP001";
        Date fromDate = new Date();
        List<Salary> mockDeletedSalaries = Collections.emptyList();
        when(salaryService.deleteSalaryByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockDeletedSalaries);
        List<Salary> result = salaryApi.deleteSalaryByEmpNoAndFromDate(empNo, fromDate);
        assertEquals(mockDeletedSalaries, result);
    }
}