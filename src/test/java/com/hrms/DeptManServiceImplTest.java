package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.beans.DeptManager;
import com.hrms.dao.DepatmentManagerDao;
import com.hrms.service.DeptManServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DeptManServiceImplTest {
    @Mock
 
    private DepatmentManagerDao deptManagerDao;
    @InjectMocks
 
    private DeptManServiceImpl deptManService;
    private DeptManager sampleDeptManager;
 
    private List<DeptManager> sampleDeptManagers;
    @BeforeEach
 
    void setUp() {
 
        sampleDeptManager = new DeptManager();
 
        sampleDeptManagers = new ArrayList<>();
 
        sampleDeptManagers.add(sampleDeptManager);
 
    }
    @Test
    void testGetAllDeptManagers() {
        when(deptManagerDao.getAllDeptManagers()).thenReturn(sampleDeptManagers);
        List<DeptManager> result = deptManService.getAllDeptManagers();

        assertNotNull(result);
        // Add more specific assertions based on the expected behavior of getAllDeptManagers()
        // For instance, you can check the size of the returned list or verify specific elements
        // Example assertions:
        assertEquals(sampleDeptManagers.size(), result.size());
        assertTrue(result.containsAll(sampleDeptManagers));
        // Add other relevant assertions as needed
    }

    @Test
 
    void testUpdateDeptManagerDetails() {
 
        String empNo = "123";
 
        String deptNo = "IT";
 
        Date newFromDate = new Date();
 
        Date newToDate = new Date();
        deptManService.updateDeptManagerDetails(empNo, deptNo, newFromDate, newToDate);
 
        verify(deptManagerDao).updateDeptManagerDetails(empNo, deptNo, newFromDate, newToDate);
 
    }
    @Test
 
    void testUpdateDeptManager() {
 
        String deptNo = "IT";
 
        String fromDate = "2023-01-01";
 
        DeptManager updatedDeptManagerDetails = new DeptManager();
        deptManService.updateDeptManager(deptNo, fromDate, updatedDeptManagerDetails);
        verify(deptManagerDao).updateDeptManager(deptNo, fromDate, updatedDeptManagerDetails);
 
    }
    @Test
 
     void testDeleteDeptManagerByEmpNoAndDeptNo() {
 
        int empNo = 1;
 
        String deptNo = "HR";
        deptManService.deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
        verify(deptManagerDao).deleteDeptManagerByEmpNoAndDeptNo(empNo, deptNo);
 
    }
 
    @Test
 
     void testGetDeptManagersByDeptNoAndFromDate() {
 
        String deptNo = "HR";
 
        Date fromDate = new Date();
 
        List<DeptManager> mockDeptManagers = Arrays.asList(new DeptManager(), new DeptManager());
 
        when(deptManagerDao.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManService.getDeptManagersByDeptNoAndFromDate(deptNo, fromDate);
 
        assertEquals(mockDeptManagers, result);
 
    }
 
    @Test
 
     void testGetDeptManagersByEmpNoAndFromDate() {
 
        int empNo = 123;
 
        Date fromDate = new Date();
 
        List<DeptManager> mockDeptManagers = Arrays.asList(new DeptManager(), new DeptManager());
 
        when(deptManagerDao.getDeptManagersByEmpNoAndFromDate(empNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManService.getDeptManagersByEmpNoAndFromDate(empNo, fromDate);
 
        assertEquals(mockDeptManagers, result);
 
    }
 
    @Test
 
     void testGetDeptManagersByEmpNoDeptNoAndFromDate() {
 
        int empNo = 123;
 
        String deptNo = "HR";
 
        Date fromDate = new Date();
 
        List<DeptManager> mockDeptManagers = Arrays.asList(new DeptManager(), new DeptManager());
 
        when(deptManagerDao.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate)).thenReturn(mockDeptManagers);
        List<DeptManager> result = deptManService.getDeptManagersByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
 
        assertEquals(mockDeptManagers, result);
 
    }
    @Test
 
     void testDeleteDeptManagerByEmpNoAndFromDate() {
 
        int empNo = 123;
 
        Date fromDate = new Date();
        deptManService.deleteDeptManagerByEmpNoAndFromDate(empNo, fromDate);
        verify(deptManagerDao, times(1)).deleteDeptManagerByEmpNoAndFromDate(empNo, fromDate);
 
    }
 
    @Test
 
     void testDeleteDeptManagerByDeptNoAndFromDate() {
 
        String deptNo = "HR";
 
        Date fromDate = new Date();
        deptManService.deleteDeptManagerByDeptNoAndFromDate(deptNo, fromDate);
        verify(deptManagerDao, times(1)).deleteDeptManagerByDeptNoAndFromDate(deptNo, fromDate);
 
    }
 
    @Test
 
     void testDeleteDeptManagerByEmpNoDeptNoAndFromDate() {
 
        int empNo = 123;
 
        String deptNo = "HR";
 
        Date fromDate = new Date();
        deptManService.deleteDeptManagerByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
        verify(deptManagerDao, times(1)).deleteDeptManagerByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);
 
    }
 

}