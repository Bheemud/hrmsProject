package com.hrms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hrms.beans.DeptManager;
import com.hrms.beans.controller.DeptManApi;
import com.hrms.service.DeptManServiceImpl;

@ExtendWith(MockitoExtension.class)
 class DeptManApiTest {
    @InjectMocks
    private DeptManApi deptManApi;
    @Mock
    private DeptManServiceImpl deptManagerService;
    @Test
     void testGetAllDeptManagers() {
        List<DeptManager> deptManagers = Arrays.asList(new DeptManager());
        when(deptManagerService.getAllDeptManagers()).thenReturn(deptManagers);
        List<DeptManager> allDeptManagers = deptManApi.getAllDeptManagers();
        assertThat(allDeptManagers).isNotEmpty();
    }
    @Test
     void testGetDeptManagerByEmpNoAndDeptNo() {
        int empNo = 123;
        String deptNo = "HR";
        DeptManager expectedDeptManager = new DeptManager();
        when(deptManagerService.getDeptManagerByEmpNoAndDeptNo(empNo, deptNo)).thenReturn(expectedDeptManager);
        DeptManager actualDeptManager = deptManApi.getDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
        assertThat(actualDeptManager).isNotNull();
    }
    @Test
    void testGetDeptManagersByEmpNoAndFromDate() {
        int empNo = 123;
        Date fromDate = new Date();
        List<DeptManager> mockDeptManagers = new ArrayList<>();
        when(deptManagerService.getDeptManagersByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManApi.getDeptManagersByEmpNoAndFromDate(empNo, fromDate);
        assertEquals(mockDeptManagers, result);
}
    @Test
    void testGetDeptManagersByDeptNoAndFromDate() {
        String deptNo = "HR";
        Date fromDate = new Date();
        List<DeptManager> mockDeptManagers = List.of(new DeptManager());
        when(deptManagerService.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManApi.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate);
        assertEquals(mockDeptManagers, result);
    }
    @Test
    void testGetDeptManagersByEmpNoDeptNoAndFromDate() {
        int empNo = 123;
        String deptNo = "HR";
        Date fromDate = new Date();
        List<DeptManager> mockDeptManagers = List.of(new DeptManager());
        when(deptManagerService.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManApi.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
        assertEquals(mockDeptManagers, result);
    }
    @Test
    void testDeleteDeptManagerByEmpNoAndDeptNo() {
        int empNo = 123;
        String deptNo = "HR";
        String result = deptManApi.deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
        verify(deptManagerService, times(1)).deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
        assertEquals("DeptManager deleted Successfully", result);
    }
    @Test
     void testUpdateDeptManagerDetails1() {
        String empno = "EMP001";
        String deptno = "DEPT001";
        Date fromDate = new Date();
        Date toDate = new Date();  
        doNothing().when(deptManagerService).updateDeptManagerDetails(eq(empno), eq(deptno), any(Date.class), any(Date.class));
        String result = deptManApi.updateDeptManagerDetails(fromDate, toDate, empno, deptno);
        verify(deptManagerService, times(1)).updateDeptManagerDetails(eq(empno), eq(deptno), any(Date.class), any(Date.class));
        assert result.equals("Employee assigned to department updated Successfully");
    }
    @SuppressWarnings("deprecation")
	@Test
     void testUpdateDeptManagerDetails() {
        String deptNo = "DEPT001";
        String fromDate = "2023-12-09";
        DeptManager updatedDeptManagerDetails = new DeptManager();
        doNothing().when(deptManagerService).updateDeptManager(eq(deptNo), eq(fromDate), any(DeptManager.class));
        ResponseEntity<String> responseEntity = deptManApi.updateDeptManagerDetails(deptNo, fromDate, updatedDeptManagerDetails);
        verify(deptManagerService, times(1)).updateDeptManager(eq(deptNo), eq(fromDate), any(DeptManager.class));
        assert responseEntity.getStatusCodeValue() == 200;
        assert responseEntity.getBody().equals("Department manager details updated successfully.");
    }
    @SuppressWarnings("deprecation")
	@Test
     void testDeleteDeptManagerByEmpNoAndFromDate() {
        int empNo = 123;
        Date fromDate = new Date();
        doNothing().when(deptManagerService).deleteDeptManagerByEmpNoAndFromDate(eq(empNo), any(Date.class));
        ResponseEntity<String> responseEntity = deptManApi.deleteDeptManagerByEmpNoAndFromDate(empNo, fromDate);
        verify(deptManagerService, times(1)).deleteDeptManagerByEmpNoAndFromDate(eq(empNo), any(Date.class));
        assert responseEntity.getStatusCodeValue() == 200;
        assert responseEntity.getBody().equals("DeptManager deleted successfully");
    }
    @SuppressWarnings("deprecation")
	@Test
     void testDeleteDeptManagerByDeptNoAndFromDate() {
        String deptNo = "DEPT001";
        Date fromDate = new Date();
        doNothing().when(deptManagerService).deleteDeptManagerByDeptNoAndFromDate(eq(deptNo), any(Date.class));
        ResponseEntity<String> responseEntity = deptManApi.deleteDeptManagerByDeptNoAndFromDate(deptNo, fromDate);
        verify(deptManagerService, times(1)).deleteDeptManagerByDeptNoAndFromDate(eq(deptNo), any(Date.class));
        assert responseEntity.getStatusCodeValue() == 200;
        assert responseEntity.getBody().equals("DeptManager deleted successfully");
    }
    @SuppressWarnings("deprecation")
	@Test
     void testDeleteDeptManagerByEmpNoDeptNoAndFromDate() {
        int empNo = 123;
        String deptNo = "DEPT001";
        Date fromDate = new Date();
        doNothing().when(deptManagerService).deleteDeptManagerByEmpNoDeptNoAndFromDate(eq(empNo), eq(deptNo), any(Date.class));
        ResponseEntity<String> responseEntity = deptManApi.deleteDeptManagerByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
        verify(deptManagerService, times(1)).deleteDeptManagerByEmpNoDeptNoAndFromDate(eq(empNo), eq(deptNo), any(Date.class));
        assert responseEntity.getStatusCodeValue() == 200;
        assert responseEntity.getBody().equals("DeptManager deleted successfully");
    }    
}
