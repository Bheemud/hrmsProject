package com.hrms.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee{

    @Id
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "first_name", length = 14)
    private String firstName;

    @Column(name = "last_name", length = 16)
    private String lastName;

    @Column(name = "email", length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "role", length = 255)
    private String role;

    public Employee() {
    }

    public Employee(int empNo, Date birthDate, Date hireDate, String firstName, String lastName, String email,
                    Gender gender) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
       
    }

    public Employee(String password, String role) {
    	 this.password = password;
         this.role = role;
    	
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public enum Gender {
        M,
        F
    }
}
