package com.hrms.beans;
 
import jakarta.persistence.*;
 
import java.io.Serializable;
import java.util.Date;
 
@SuppressWarnings("serial")
@Entity
@Table(name = "titles")
@IdClass(TitlesId.class)
public class Titles implements Serializable {
 
    @Id
    @Column(name = "emp_no")
    private String empNo;
 
    @Id
    @Column(name = "from_date")
    private Date fromDate;
 
    @Column(name = "to_date")
    private Date toDate;
 
    @Column(name = "title")
    private String title;
 
    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;
 
    public Titles() {
    }
 
    public Titles(String empNo, Date fromDate, Date toDate, String title, Employee employee) {
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.title = title;
        this.employee = employee;
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
 
    public Date getToDate() {
        return toDate;
    }
 
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public Employee getEmployee() {
        return employee;
    }
 
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
 
    @Override
    public String toString() {
        return "Titles{" +
                "empNo='" + empNo + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", title='" + title + '\'' +
                ", employee=" + employee +
                '}';
    }
}