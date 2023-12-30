package com.hrms.beans;


import jakarta.persistence.*;

@Table(name= "departments")
@Entity
public class Departments {

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;



	public Departments() {
		super();
	}

	public Departments(String deptNo, String deptName) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;

}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Departments [deptNo=" + deptNo + ", deptName=" + deptName + ", getDeptNo()=" + getDeptNo()
				+ ", getDeptName()=" + getDeptName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

    
}
