package com.example.cruddemo.service;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.dao.EmployeeDAOImpl;
import com.example.cruddemo.model.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("employeeService")
@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employee != null) {
            employeeDAO.addEmployee(employee);
        } else {
            System.out.println("Employee object is null. Cannot add.");
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee != null && employee.getEmployeeCode() > 0) {
            employeeDAO.updateEmployee(employee);
        } else {
            System.out.println("Invalid employee data for update.");
        }
    }

    @Override
    public void deleteEmployee(int id) {
        if (id > 0) {
            employeeDAO.deleteEmployee(id);
        } else {
            System.out.println("Invalid ID for deletion.");
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

}
