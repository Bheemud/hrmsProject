package com.hrms.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.beans.Departments;

@Repository
public interface DepartmentDao extends JpaRepository<Departments, String> {
    

	Optional<Departments> findByDeptNo(String deptNo);
	List<Departments> findByDeptName(String deptName);

}
