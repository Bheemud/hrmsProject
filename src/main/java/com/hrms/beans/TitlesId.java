package com.hrms.beans;
 
import java.io.Serializable;
import java.util.*;
@SuppressWarnings("serial")
public class TitlesId implements Serializable {
 
    private String empNo;

    private Date fromDate;
 
    public TitlesId() {

    }
 
    public TitlesId(String empNo, Date fromDate) {

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

        TitlesId titlesId = (TitlesId) o;

        return Objects.equals(empNo, titlesId.empNo) && Objects.equals(fromDate, titlesId.fromDate);

    }
 
    @Override

    public int hashCode() {

        return Objects.hash(empNo, fromDate);

    }

}
