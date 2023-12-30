package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import com.hrms.beans.DeptEmployee;
import com.hrms.beans.controller.DeparmentEmployeApi;
import com.hrms.service.DepartmentEmployeeServiceImpl;
 
@ExtendWith(MockitoExtension.class)
 class DeparmentEmployeApiTest {
	
	@InjectMocks
    private DeparmentEmployeApi deptEmpApi;
 
    @Mock
    private DepartmentEmployeeServiceImpl deptempService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testCreateDeptemp() {
        DeptEmployee inputDeptEmp = new DeptEmployee();
        DeptEmployee mockedResult = new DeptEmployee();
        when(deptempService.createDeptEmp(inputDeptEmp)).thenReturn(mockedResult);
        DeptEmployee result = deptEmpApi.createDeptemp(inputDeptEmp);
        verify(deptempService, times(1)).createDeptEmp(inputDeptEmp);
    }

    @Test
    public void testGetAllDept_emp() {
        List<DeptEmployee> mockedResult = new ArrayList<>();
        mockedResult.add(new DeptEmployee());
        when(deptempService.fetchAllDeptEmp()).thenReturn(mockedResult);
        List<DeptEmployee> result = deptEmpApi.getAllDept_emp();
        verify(deptempService, times(1)).fetchAllDeptEmp();
    }
    @Test
    public void testSearchByEmpnoAndDeptno() {
        int empNo = 123; 
        String deptNo = "HR";
        List<DeptEmployee> expectedResult = new ArrayList<>();
        when(deptempService.searchByEmpnoAndDeptno(empNo, deptNo)).thenReturn(expectedResult);
        List<DeptEmployee> result = deptEmpApi.searchByEmpnoAndDeptno(empNo, deptNo);
        verify(deptempService, times(1)).searchByEmpnoAndDeptno(empNo, deptNo);
    }
    @Test
    void testSearchByDeptnoAndFromdate() {
        String deptNo = "D001";
        String fromDate = "2023-01-01";
        Date fromDateSql = Date.valueOf(fromDate);
        DeptEmployee deptEmployee1 = new DeptEmployee();
        DeptEmployee deptEmployee2 = new DeptEmployee();
        List<DeptEmployee> deptEmployeeList = Arrays.asList(deptEmployee1, deptEmployee2);
        when(deptempService.searchByDeptnoAndFromdate(deptNo, fromDateSql)).thenReturn(deptEmployeeList);
        List<DeptEmployee> result = deptEmpApi.searchByDeptnoAndFromdate(deptNo, fromDate);
        verify(deptempService, times(1)).searchByDeptnoAndFromdate(deptNo, fromDateSql);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(deptEmployee1, result.get(0));
        assertEquals(deptEmployee2, result.get(1));
    }
    @Test
    void testSearchByEmpNoDeptNoAndFromDate() {
        int empNo = 123;
        String deptNo = "D001";
        String fromDate = "2023-01-01";
        Date fromDateSql = Date.valueOf(fromDate);
        DeptEmployee deptEmployee1 = new DeptEmployee();
        DeptEmployee deptEmployee2 = new DeptEmployee();
        List<DeptEmployee> deptEmployeeList = Arrays.asList(deptEmployee1, deptEmployee2);
        when(deptempService.searchByEmpnoDeptNoAndFromDate(empNo, deptNo, fromDateSql)).thenReturn(deptEmployeeList);
        List<DeptEmployee> result = deptEmpApi.searchByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
        verify(deptempService, times(1)).searchByEmpnoDeptNoAndFromDate(empNo, deptNo, fromDateSql);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(deptEmployee1, result.get(0));
        assertEquals(deptEmployee2, result.get(1));
    }
    @Test
    void testSearchByEmpnoAndFromdate() {
        // Sample data for testing
        int empNo = 123;
        String fromDate = "2023-01-01";
        Date fromDateSql = Date.valueOf(fromDate);
        DeptEmployee deptEmployee1 = new DeptEmployee();
        DeptEmployee deptEmployee2 = new DeptEmployee();
        List<DeptEmployee> deptEmployeeList = Arrays.asList(deptEmployee1, deptEmployee2);
        when(deptempService.searchByEmpnoAndFromdate(empNo, fromDateSql)).thenReturn(deptEmployeeList);
        List<DeptEmployee> result = deptEmpApi.searchByEmpnoAndFromdate(empNo, fromDate);
        verify(deptempService, times(1)).searchByEmpnoAndFromdate(empNo, fromDateSql);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(deptEmployee1, result.get(0));
        assertEquals(deptEmployee2, result.get(1));
    }
    
    @Test
    void testUpdateDeptEmployee() {
        int empNo = 123;
        String deptNo = "D001";
        String fromDate = "2023-01-01";
        String toDate = "2023-02-01";
        when(deptempService.updateDeptEmployee(deptNo, empNo, Date.valueOf(LocalDate.parse(fromDate)), Date.valueOf(LocalDate.parse(toDate))))
                .thenReturn(new DeptEmployee());
        DeptEmployee result = deptEmpApi.updateDeptEmployee(empNo, deptNo, fromDate, toDate);
        verify(deptempService, times(1)).updateDeptEmployee(deptNo, empNo,
                Date.valueOf(LocalDate.parse(fromDate)), Date.valueOf(LocalDate.parse(toDate)));
        assertNotNull(result);
    }
    @Test
    void testDeleteDeptEmp() {
        int empNo = 123;
        String deptNo = "D001";
        when(deptempService.deleteDeptEmp(deptNo, empNo)).thenReturn("DeptEmp deleted successfully");
        String result = deptEmpApi.deleteDeptEmp(empNo, deptNo);
        verify(deptempService, times(1)).deleteDeptEmp(deptNo, empNo);
        assertNotNull(result);
        assertEquals("DeptEmp deleted successfully", result);
    }
    @Test
    void testDeleteDeptEmpByEmpNoAndFromDate() throws ParseException {
        int empNo = 123;
        String fromDate = "2023-01-01";
        doNothing().when(deptempService).deleteByEmpNoAndFromDate(eq(empNo), any(Date.class));
        ResponseEntity<String> responseEntity = deptEmpApi.deleteDeptEmpByEmpNoAndFromDate(empNo, fromDate);
        verify(deptempService, times(1)).deleteByEmpNoAndFromDate(eq(empNo), any(Date.class));
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("DeptEmp deleted successfully", responseEntity.getBody());
    }

    @Test
    void testDeleteDeptEmpByEmpNoAndFromDateWithInvalidDateFormat() throws ParseException {
        int empNo = 123;
        String fromDate = "invalid-date-format";
        ResponseEntity<String> responseEntity = deptEmpApi.deleteDeptEmpByEmpNoAndFromDate(empNo, fromDate);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid date format. Please use the format 'yyyy-MM-dd'.", responseEntity.getBody());
    }
    
    @Test
    void testDeleteDeptDeptByEmpNoAndFromDate_InvalidDateFormat() throws ParseException {
        String deptNo = "D001";
        String fromDate = "2023/01/01";
        ResponseEntity<String> responseEntity = deptEmpApi.deleteDeptDeptByEmpNoAndFromDate(deptNo, fromDate);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid date format. Please use the format 'yyyy-MM-dd'.", responseEntity.getBody());
    }
    
    @Test
    void testDeleteDeptManagerEmpNoAndByDeptNoAndFromDate() {
        int empNo = 123;
        String deptNo = "D001";
        String fromDate = "2023-01-01";
        Date fromDateSql = Date.valueOf(fromDate);
        doNothing().when(deptempService).deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDateSql);
        ResponseEntity<String> responseEntity = deptEmpApi.deleteDeptManagerEmpNoAndByDeptNoAndFromDate(empNo, deptNo, fromDate);
        verify(deptempService, times(1)).deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDateSql);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("DeptManager deleted successfully", responseEntity.getBody());
    }

    @Test
    void testDeleteDeptManagerEmpNoAndByDeptNoAndFromDate_InvalidDateFormat() {
        int empNo = 123;
        String deptNo = "D001";
        String fromDate = "invalid-date-format";
        ResponseEntity<String> responseEntity = deptEmpApi.deleteDeptManagerEmpNoAndByDeptNoAndFromDate(empNo, deptNo, fromDate);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid date format. Please use the format 'yyyy-MM-dd'.", responseEntity.getBody());
    }
}
