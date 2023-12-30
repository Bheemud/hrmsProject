package com.hrms.beans;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Embeddable
public class DeptManagerId implements Serializable {
	@Column(name = "dept_no")
	private String departmentNumber;

	@Column(name = "emp_no")
	private String employeeNumber;

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public DeptManagerId(String departmentNumber) {
		super();
		this.departmentNumber = departmentNumber;

	}
	
	public DeptManagerId() {
		super();
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentNumber, employeeNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeptManagerId other = (DeptManagerId) obj;
		return Objects.equals(departmentNumber, other.departmentNumber)
				&& Objects.equals(employeeNumber, other.employeeNumber);
	}

	@Override
	public String toString() {
		return "DeptManagerId [departmentNumber=" + departmentNumber + ", employeeNumber=" + employeeNumber + "]";
	}

}
