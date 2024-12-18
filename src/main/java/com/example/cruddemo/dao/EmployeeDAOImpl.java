package com.example.cruddemo.dao;

import com.example.cruddemo.model.Employee;
import com.example.cruddemo.util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO mt_employees (name, age, date_of_birth) VALUES (?, ?, ?)";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, employee.getEmployeeName());
            pst.setInt(2, employee.getEmployeeAge());
            pst.setDate(3, Date.valueOf(employee.getDateOfBirth())); // Chuyển LocalDate -> java.sql.Date
            pst.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE mt_employees SET name = ?, age = ?, date_of_birth = ? WHERE id = ?";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, employee.getEmployeeName());
            pst.setInt(2, employee.getEmployeeAge());
            pst.setDate(3, Date.valueOf(employee.getDateOfBirth())); // Chuyển LocalDate -> java.sql.Date
            pst.setInt(4, employee.getEmployeeCode());
            pst.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM mt_employees WHERE id = ?";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM mt_employees";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeCode(rs.getInt("id"));
                employee.setEmployeeName(rs.getString("name"));
                employee.setEmployeeAge(rs.getInt("age"));
                employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate()); // Chuyển java.sql.Date -> LocalDate
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        String sql = "SELECT * FROM mt_employees WHERE id = ?";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeCode(rs.getInt("id"));
                    employee.setEmployeeName(rs.getString("name"));
                    employee.setEmployeeAge(rs.getInt("age"));
                    employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate()); // Chuyển java.sql.Date -> LocalDate
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
