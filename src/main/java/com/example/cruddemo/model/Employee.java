package com.example.cruddemo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int employeeCode;
    private String employeeName;
    private int employeeAge;
    private LocalDate dateOfBirth; // Sử dụng LocalDate

    public int getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(int employeeCode, String employeeName, int employeeAge, LocalDate dateOfBirth) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {}
}
