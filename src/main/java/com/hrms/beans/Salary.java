package com.hrms.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salaries")
@IdClass(SalaryId.class)
public class Salary {

    @Id
    @Column(name = "emp_no")
    private String empNo;

    @Id
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "salary")
    private int salary;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    public Salary() {
        
    }

    public Salary(String empNo, Date fromDate, int salary, Date toDate, Employee employee) {
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.salary = salary;
        this.toDate = toDate;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "empNo='" + empNo + '\'' +
                ", fromDate=" + fromDate +
                ", salary=" + salary +
                ", toDate=" + toDate +
                ", employee=" + employee +
                '}';
    }
}
