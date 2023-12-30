package com.hrms;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.service.DepartmentServiceImpl;

@SpringBootTest
class HrmsApplicationTests {
	
	 	@Autowired
	    private DepartmentServiceImpl departmentService;

	@Test
	void contextLoads() {
		 assertNotNull(departmentService);
	}

}
