package com.hrms.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dept_manager")
public class DeptManager {
    @EmbeddedId
    private DeptManagerId id;

    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", insertable = false, updatable = false)
    private Departments department;

    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public DeptManagerId getId() {
        return id;
    }

    public void setId(DeptManagerId id) {
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
}
