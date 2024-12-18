package com.example.cruddemo.service;

import com.example.cruddemo.model.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);    // Thêm nhân viên
    void updateEmployee(Employee employee); // Sửa nhân viên
    void deleteEmployee(int id);            // Xóa nhân viên
    List<Employee> getAllEmployees();       // Lấy danh sách nhân viên
}
