package com.hrms.service;

import java.util.List;

import com.hrms.beans.Departments;

public interface DepartmentService {
	
	Departments createDepartment(Departments department);
    List<Departments> getAllDepartments();
    Departments updateDepartments(String deptNo, Departments departments);
    String deleteDepartmentString(String depNo);
    Departments getDepartmentByDeptNo(String deptNo);
    List<Departments> getDepartmentsByName(String deptName);
    Departments updateDepartmentsByName(String deptName, Departments departments);


	String deleteDepartment(String deptName);

}
