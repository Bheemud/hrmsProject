package com.hrms;

 
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hrms.beans.Departments;
import com.hrms.beans.controller.DepartmentApi;
import com.hrms.service.DepartmentService;
 
@ExtendWith(MockitoExtension.class)
 class DepartmentApiTest {
 
    @InjectMocks
    private DepartmentApi departmentApi;
 
    @Mock
    private DepartmentService deptService;
 
    @Test
     void testCreateDepartment() {
        Departments department = new Departments();
        when(deptService.createDepartment(department)).thenReturn(department);
 
        Departments createdDepartment = departmentApi.createDepartment(department);
 
        assertThat(createdDepartment).isNotNull();
    }
 
    @Test
     void testGetAllDepartments() {
        List<Departments> departments = Arrays.asList(new Departments(/* department details here */));
        when(deptService.getAllDepartments()).thenReturn(departments);
 
        List<Departments> allDepartments = departmentApi.getAllDepartments();
 
        assertThat(allDepartments).isNotEmpty();
    }
    @Test
     void testUpdateDepartments() {
        String deptNo = "123";
        Departments departmentToUpdate = new Departments();
        when(deptService.updateDepartments(deptNo, departmentToUpdate)).thenReturn(departmentToUpdate);
 
        Departments updatedDepartment = departmentApi.updateDepartments(deptNo, departmentToUpdate);
 
        assertThat(updatedDepartment).isNotNull();
    }
    @Test
     void testDeleteDepartments() {
        String deptNoToDelete = "123";
        when(deptService.deleteDepartmentString(deptNoToDelete)).thenReturn("Department deleted successfully");
 
        String resultMessage = departmentApi.deleteDepartments(deptNoToDelete);
 
        assertThat(resultMessage).isEqualTo("Department deleted successfully");
    }
    @Test
     void testGetDepartmentByDeptNo() {
        String deptNo = "123";
        Departments expectedDepartment = new Departments(/* details of the department */);
        when(deptService.getDepartmentByDeptNo(deptNo)).thenReturn(expectedDepartment);
 
        Departments actualDepartment = departmentApi.getDepartmentByDeptNo(deptNo);
 
        assertThat(actualDepartment).isEqualTo(expectedDepartment);
    }
    @Test
     void testGetDepartmentsByName() {
        String deptName = "DepartmentName";
        List<Departments> expectedDepartments = Arrays.asList(new Departments(/* department details here */));
        when(deptService.getDepartmentsByName(deptName)).thenReturn(expectedDepartments);
 
        List<Departments> actualDepartments = departmentApi.getDepartmentsByName(deptName);
 
        assertThat(actualDepartments).isNotEmpty();
        assertThat(actualDepartments).isEqualTo(expectedDepartments);
    }
    @Test
    void testUpdateDepartmentByName() {
        String deptName = "DepartmentName";
        Departments departmentToUpdate = new Departments(/* details of the department to update */);

        when(deptService.updateDepartmentsByName(deptName, departmentToUpdate))
            .thenReturn(departmentToUpdate);

        Departments updatedDepartment = departmentApi.updateDepartmentByName(deptName, departmentToUpdate);

        assertThat(updatedDepartment.getDeptName()).isEqualTo(deptName);
    }

    @Test
     void testDeleteDepartmentByName() {
        String deptNameToDelete = "DepartmentName";
        when(deptService.deleteDepartmentString(deptNameToDelete)).thenReturn("Department deleted successfully");
 
        String resultMessage = departmentApi.deleteDepartmentByName(deptNameToDelete);
 
        assertThat(resultMessage).isEqualTo("Department deleted successfully");
    }
 
 
 
}
 