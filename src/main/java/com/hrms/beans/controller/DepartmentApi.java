package com.hrms.beans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.Departments;
import com.hrms.service.DepartmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/Department")
@SecurityRequirement(name = "Bearer Authentication")
public class DepartmentApi {

    @Autowired
    private DepartmentService deptService;

    @PostMapping
    public Departments createDepartment(@RequestBody Departments dept) {
        return deptService.createDepartment(dept);
    }

    @GetMapping
    public List<Departments> getAllDepartments() {
        return deptService.getAllDepartments();
    }

    @PutMapping("/{deptNo}")
    public Departments updateDepartments(@PathVariable("deptNo") String deptNo, @RequestBody Departments dept) {
        return deptService.updateDepartments(deptNo, dept);
    }

    @DeleteMapping("/{deptNo}")
    public String deleteDepartments(@PathVariable("deptNo") String deptNo) {
        return deptService.deleteDepartmentString(deptNo);
    }

    @GetMapping("/{deptNo}")
    public Departments getDepartmentByDeptNo(@PathVariable("deptNo") String deptNo) {
        return deptService.getDepartmentByDeptNo(deptNo);
    }

    @GetMapping("/name/{deptName}")
    public List<Departments> getDepartmentsByName(@PathVariable("deptName") String deptName) {
        return deptService.getDepartmentsByName(deptName);
    }

    @PutMapping("/name/{deptName}")
    public Departments updateDepartmentByName(@PathVariable("deptName") String deptName, @RequestBody Departments dept) {
        return deptService.updateDepartmentsByName(deptName, dept);
    }

    @DeleteMapping("/name/{deptName}")
    public String deleteDepartmentByName(@PathVariable("deptName") String deptName) {
        return deptService.deleteDepartmentString(deptName);
    }
}



