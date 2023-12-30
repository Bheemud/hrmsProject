package com.hrms;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.hrms.beans.DeptEmployee;
import com.hrms.beans.DeptEmployeeId;
import com.hrms.dao.DepartmentEmployeeDao;
import com.hrms.exception.EmployeeDataNotFoundException;
import com.hrms.service.DepartmentEmployeeServiceImpl;
 
@SpringBootTest
 class DepartmentEmployeeServiceTest {
 
	 @Mock
	    private DepartmentEmployeeDao deptEmpDaoMock;

	    @InjectMocks
	    private DepartmentEmployeeServiceImpl deptEmpService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCreateDeptEmp() {
	        DeptEmployee deptEmp = new DeptEmployee();
	        deptEmp.setId(new DeptEmployeeId("deptNo", 1));
	        when(deptEmpDaoMock.save(any(DeptEmployee.class))).thenReturn(deptEmp);
	        DeptEmployee result = deptEmpService.createDeptEmp(deptEmp);
	        verify(deptEmpDaoMock).save(deptEmp);
	        assertNotNull(result);
	    }

	    @Test
	    public void testSearchByEmpnoAndDeptno() {
	        List<DeptEmployee> deptEmployees = Arrays.asList(new DeptEmployee(), new DeptEmployee());
	        when(deptEmpDaoMock.findByIdEmpnoAndIdDeptNo(anyInt(), anyString())).thenReturn(deptEmployees);
	        List<DeptEmployee> result = deptEmpService.searchByEmpnoAndDeptno(1, "deptNo");
	        verify(deptEmpDaoMock).findByIdEmpnoAndIdDeptNo(1, "deptNo");
	        assertNotNull(result);
	        assertEquals(2, result.size());
	    }

	    @Test
	    public void testDeleteDeptEmp() {
	        DeptEmployeeId deptEmployeeId = new DeptEmployeeId("deptNo", 1);
	        Optional<DeptEmployee> optionalDeptEmployee = Optional.of(new DeptEmployee());
	        when(deptEmpDaoMock.findById(deptEmployeeId)).thenReturn(optionalDeptEmployee);
	        String result = deptEmpService.deleteDeptEmp("deptNo", 1);
	        verify(deptEmpDaoMock).delete(optionalDeptEmployee.get());
	        assertEquals("DeptEmployee deleted successfully", result);
	    }


	   
	    @Test
	    public void testDeleteByEmpNoAndFromDate() {
	        doNothing().when(deptEmpDaoMock).deleteByEmpNoAndFromDate(anyInt(), any());
	        assertDoesNotThrow(() -> deptEmpService.deleteByEmpNoAndFromDate(1, new java.sql.Date(System.currentTimeMillis())));
	        verify(deptEmpDaoMock).deleteByEmpNoAndFromDate(eq(1), any(java.sql.Date.class));
	    }

	    @Test
	    public void testDeleteByDeptNoAndFromDate() {
	        doNothing().when(deptEmpDaoMock).deleteByDeptNoAndFromDate(anyString(), any());
	        assertDoesNotThrow(() -> deptEmpService.deleteByDeptNoAndFromDate("deptNo", new java.sql.Date(System.currentTimeMillis())));
	        verify(deptEmpDaoMock).deleteByDeptNoAndFromDate(eq("deptNo"), any(java.sql.Date.class));
	        verify(deptEmpDaoMock).deleteByDeptNoAndFromDate(eq("deptNo"), any(java.sql.Date.class));
	        verifyNoMoreInteractions(deptEmpDaoMock);
	    }


	    @Test
	    public void testDeleteByEmpNoAndDeptNoAndFromDate() {
	        doNothing().when(deptEmpDaoMock).deleteByEmpNoDeptNoAndFromDate(anyInt(), anyString(), any());
	        assertDoesNotThrow(() -> deptEmpService.deleteByEmpNoAndDeptNoAndFromDate(1, "deptNo", new java.sql.Date(System.currentTimeMillis())));
	        verify(deptEmpDaoMock).deleteByEmpNoDeptNoAndFromDate(eq(1), eq("deptNo"), any(java.sql.Date.class));
	        verify(deptEmpDaoMock).deleteByEmpNoDeptNoAndFromDate(eq(1), eq("deptNo"), any(java.sql.Date.class));
	        verifyNoMoreInteractions(deptEmpDaoMock);
	    }
	    
	 
	    @Test
	    public void testSearchByDeptnoAndFromdate() {
	        String deptNo = "YourDeptNo";
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        List<DeptEmployee> mockResult = Arrays.asList(new DeptEmployee()); // Add your mocked data
	        when(deptEmpDaoMock.findByDepartmentDeptNoAndFromDate(deptNo, fromDate)).thenReturn(mockResult);
	        List<DeptEmployee> result = deptEmpService.searchByDeptnoAndFromdate(deptNo, fromDate);
	        verify(deptEmpDaoMock, times(1)).findByDepartmentDeptNoAndFromDate(deptNo, fromDate);
	        assertEquals(mockResult, result);
	    }

	    @Test
	    public void testSearchByDeptnoAndFromdateNoDataFound() {
	        String deptNo = "YourDeptNo";
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        when(deptEmpDaoMock.findByDepartmentDeptNoAndFromDate(deptNo, fromDate)).thenReturn(Collections.emptyList());
	        try {
	        	deptEmpService.searchByDeptnoAndFromdate(deptNo, fromDate);
	        } catch (EmployeeDataNotFoundException e) {
	            assertTrue(e.getMessage().contains("No data found for deptno: " + deptNo + " and for fromDate: " + fromDate));
	        }
	    }
	    
	    @Test
	    public void testSearchByEmpnoDeptNoAndFromDate() {
	        int empNo = 123;
	        String deptNo = "YourDeptNo";
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        List<DeptEmployee> mockResult = Arrays.asList(new DeptEmployee()); // Add your mocked data
	        when(deptEmpDaoMock.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate)).thenReturn(mockResult);
	        List<DeptEmployee> result = deptEmpService.searchByEmpnoDeptNoAndFromDate(empNo, deptNo, fromDate);
	        verify(deptEmpDaoMock, times(1)).findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate);
	        assertEquals(mockResult, result);
	    }

	    @Test
	    public void testSearchByEmpnoDeptNoAndFromDateNoDataFound() {
	        int empNo = 123;
	        String deptNo = "YourDeptNo";
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        when(deptEmpDaoMock.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate)).thenReturn(Collections.emptyList());
	        try {
	        	deptEmpService.searchByEmpnoDeptNoAndFromDate(empNo, deptNo, fromDate);
	        } catch (EmployeeDataNotFoundException e) {
	            assertTrue(e.getMessage().contains("No data found for empno: " + empNo + " , deptno: " + deptNo + " and for fromDate: " + fromDate));
	        }
	    }
	    
	    @Test
	    public void testSearchByEmpnoAndFromdate() {
	        int empNo = 123;
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        List<DeptEmployee> mockResult = Arrays.asList(new DeptEmployee()); // Add your mocked data
	        when(deptEmpDaoMock.findByEmployee_EmpNoAndFromDate(empNo, fromDate)).thenReturn(mockResult);
	        List<DeptEmployee> result = deptEmpService.searchByEmpnoAndFromdate(empNo, fromDate);
	        verify(deptEmpDaoMock, times(1)).findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	        assertEquals(mockResult, result);
	    }

	    @Test
	    public void testSearchByEmpnoAndFromdateNoDataFound() {
	        int empNo = 123;
	        java.sql.Date fromDate = new java.sql.Date(System.currentTimeMillis());
	        when(deptEmpDaoMock.findByEmployee_EmpNoAndFromDate(empNo, fromDate)).thenReturn(Collections.emptyList());
	        try {
	        	deptEmpService.searchByEmpnoAndFromdate(empNo, fromDate);
	        } catch (EmployeeDataNotFoundException e) {
	            assertTrue(e.getMessage().contains("No data found for empno: " + empNo + " and fromDate: " + fromDate));
	        }
	    }
	    
	    
	    @Test
	    void testUpdateDeptEmployee() {
	        String deptNo = "D001";
	        int empNo = 123;
	        Date newFromDate = Date.valueOf("2023-01-01");
	        Date newToDate = Date.valueOf("2023-12-31");
	        DeptEmployeeId deptEmployeeId = new DeptEmployeeId(deptNo, empNo);
	        DeptEmployee existingDeptEmployee = new DeptEmployee();
	        when(deptEmpDaoMock.findById(deptEmployeeId)).thenReturn(Optional.of(existingDeptEmployee));
	        when(deptEmpDaoMock.save(any(DeptEmployee.class))).thenAnswer(invocation -> invocation.getArgument(0));
	        DeptEmployee updatedDeptEmployee = deptEmpService.updateDeptEmployee(deptNo, empNo, newFromDate, newToDate);
	        verify(deptEmpDaoMock, times(1)).findById(deptEmployeeId);
	        verify(deptEmpDaoMock, times(1)).save(any(DeptEmployee.class));
	        assertNotNull(updatedDeptEmployee);
	        assertEquals(newFromDate, updatedDeptEmployee.getFromDate());	
	        assertEquals(newToDate, updatedDeptEmployee.getToDate());
	    }
	    
	    @Test
	    void testFetchAllDeptEmp() {
	        List<DeptEmployee> mockDeptEmployees = Arrays.asList(
	                new DeptEmployee(),
	                new DeptEmployee()
	        );
	        when(deptEmpDaoMock.findAll()).thenReturn(mockDeptEmployees);
	        List<DeptEmployee> result = deptEmpService.fetchAllDeptEmp();
	        verify(deptEmpDaoMock, times(1)).findAll();
	        Assertions.assertEquals(mockDeptEmployees, result);
	    }

	    @Test
	    void testFetchAllDeptEmpDataAccessException() {
	        when(deptEmpDaoMock.findAll()).thenReturn(Collections.emptyList());
	        List<DeptEmployee> result = deptEmpService.fetchAllDeptEmp();
	        verify(deptEmpDaoMock, times(1)).findAll();
	        Assertions.assertTrue(result.isEmpty());
	    }
	    
}