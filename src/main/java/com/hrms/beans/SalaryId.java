package com.hrms.beans;

import java.io.Serializable;
import java.util.*;


@SuppressWarnings("serial")
public class SalaryId implements Serializable {

    private String empNo;
    private Date fromDate;

    public SalaryId() {
        
    }

    public SalaryId(String empNo, Date fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return Objects.equals(empNo, salaryId.empNo) && Objects.equals(fromDate, salaryId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}
