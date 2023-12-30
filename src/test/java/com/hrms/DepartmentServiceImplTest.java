package com.hrms;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.hrms.beans.Departments;
import com.hrms.dao.DepartmentDao;
import com.hrms.service.DepartmentServiceImpl;
 
class DepartmentServiceImplTest {
 
    @Mock
    private DepartmentDao deptDao;
 
    @InjectMocks
    private DepartmentServiceImpl departmentService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void createDepartment() {
        Departments departmentToCreate = new Departments();
        when(deptDao.save(any(Departments.class))).thenReturn(departmentToCreate);
 
        Departments createdDepartment = departmentService.createDepartment(departmentToCreate);
 
        assertNotNull(createdDepartment);
        verify(deptDao, times(1)).save(any(Departments.class));
    }
 
    @Test
    void getAllDepartments() {
        List<Departments> departmentList = new ArrayList<>();
        when(deptDao.findAll()).thenReturn(departmentList);
 
        List<Departments> retrievedDepartments = departmentService.getAllDepartments();
 
        assertNotNull(retrievedDepartments);
        verify(deptDao, times(1)).findAll();
    }
 
    @Test
    void updateDepartments() {
        String deptNo = "123";
        Departments existingDepartment = new Departments();
        
        when(deptDao.findByDeptNo(deptNo)).thenReturn(Optional.of(existingDepartment));
        when(deptDao.save(any(Departments.class))).thenReturn(existingDepartment);

        Departments updatedDepartment = departmentService.updateDepartments(deptNo, new Departments());

        assertNotNull(updatedDepartment);
        verify(deptDao, times(1)).findByDeptNo(deptNo);
        verify(deptDao, times(1)).save(any(Departments.class));
    }
 
    @Test
    void deleteDepartmentString() {
        String depNo = "123";
        
        when(deptDao.findById(depNo)).thenReturn(Optional.of(new Departments()));
        String result = departmentService.deleteDepartmentString(depNo);

        assertNotNull(result);
        verify(deptDao, times(1)).findById(depNo);
        verify(deptDao, times(1)).deleteById(depNo);
    }
 
    @Test
    void deleteDepartment() {
        String deptName = "HR";

        when(deptDao.findById(deptName)).thenReturn(Optional.of(new Departments()));
        String result = departmentService.deleteDepartment(deptName);

        assertNotNull(result);
        verify(deptDao, times(1)).findById(deptName);
        verify(deptDao, times(1)).deleteById(deptName);
    }
 
    @Test
    void getDepartmentByDeptNo() {
        String deptNo = "123";

        when(deptDao.findByDeptNo(deptNo)).thenReturn(Optional.of(new Departments()));
        Departments retrievedDepartment = departmentService.getDepartmentByDeptNo(deptNo);

        assertNotNull(retrievedDepartment);
        verify(deptDao, times(1)).findByDeptNo(deptNo);
    }
 
    @Test
    void getDepartmentsByName() {
        String deptName = "HR";

        when(deptDao.findByDeptName(deptName)).thenReturn(new ArrayList<>());
        List<Departments> retrievedDepartments = departmentService.getDepartmentsByName(deptName);

        assertNotNull(retrievedDepartments);
        verify(deptDao, times(1)).findByDeptName(deptName);
    }
    @Test
     void testUpdateDepartmentsByName() {
        String deptName = "exampleDeptName";
        Departments departments = new Departments();
        Optional<Departments> optionalDepartment = Optional.of(new Departments());
 
        when(deptDao.findByDeptNo(deptName)).thenReturn(optionalDepartment);
        when(deptDao.save(any(Departments.class))).thenReturn(departments);
 
        Departments result = departmentService.updateDepartmentsByName(deptName, departments);
 
        assertNotNull(result);
        verify(deptDao, times(1)).findByDeptNo(deptName);
        verify(deptDao, times(1)).save(any(Departments.class));
    }
 
    
 

}