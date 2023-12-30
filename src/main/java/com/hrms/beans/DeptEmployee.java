package com.hrms.beans;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
 
 
@Entity
@Table(name = "dept_emp")
public class DeptEmployee {
    @EmbeddedId
    private DeptEmployeeId id;
 
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", insertable = false, updatable = false)
    private Departments department;
 
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;
 
    @Column(name = "from_date")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date fromDate;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @Column(name = "to_date")
    private Date toDate;
 
    public DeptEmployeeId getId() {
        return id;
    }
 
    public void setId(DeptEmployeeId id) {
        this.id = id;
    }
 
    public Departments getDepartment() {
        return department;
    }
 
    public void setDepartment(Departments department) {
        this.department = department;
    }
 
    public Employee getEmployee() {
        return employee;
    }
 
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
 
    public Date getFromDate() {
        return fromDate;
    }
 
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
 
    public Date getToDate() {
        return toDate;
    }
 
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

	public DeptEmployee(DeptEmployeeId id, Departments department, Employee employee, Date fromDate, Date toDate) {
		super();
//		this.id = id;
		this.department = department;
		this.employee = employee;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public DeptEmployee() {
		super();
	}
    
 
	
}