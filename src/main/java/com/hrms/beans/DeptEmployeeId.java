package com.hrms.beans;

import java.io.Serializable;
import java.util.Objects;
 
import jakarta.persistence.*;
 
@SuppressWarnings("serial")
@Embeddable
public class DeptEmployeeId implements Serializable {
    @Column(name = "dept_no")
    private String deptNo;
 
    @Column(name = "emp_no")
    private int empno;
 
    public DeptEmployeeId() {
        super();
    }
 
    public DeptEmployeeId(String deptNo, int empno) {
		super();
		this.deptNo = deptNo;
		this.empno = empno;
	}
 
	public String getDeptNo() {
        return deptNo;
    }
 
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
 
    public void setEmpNo(int empno) {
		this.empno = empno;
	}

    
	@Override
	public int hashCode() {
		return Objects.hash(deptNo, empno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeptEmployeeId other = (DeptEmployeeId) obj;
		return Objects.equals(deptNo, other.deptNo) && empno == other.empno;
	}

	@Override
	public String toString() {
		return "DeptEmployeeId [deptNo=" + deptNo + ", empNo=" + empno + "]";
	}

	
}