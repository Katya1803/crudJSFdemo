package com.example.cruddemo.bean;

import com.example.cruddemo.model.Employee;
import com.example.cruddemo.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import java.time.LocalDate;

@Named("employeeBean")
@ApplicationScoped
public class EmployeeBean implements Serializable {
    private List<Employee> employees;
    private Employee employee;


    @Inject
    private EmployeeService employeeService;

    @PostConstruct
    public void init() {
        employee = new Employee(); //bind cho form
        loadEmployees();
        System.out.println("Employee bean loaded");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Employee bean destroyed");
    }
    // Get nhân viên
    public void loadEmployees() {
        employees = employeeService.getAllEmployees();
    }

    // Thêm/ Sửa nhân viên
    public void addEmployee() {
        if (employee.getEmployeeCode() == 0) {
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        loadEmployees();
        employee = new Employee(); // Reset form
    }
    public void updateEmployee() {
        employeeService.updateEmployee(employee);
        loadEmployees();
        employee = new Employee();
    }

    // Xóa nhân viên
    public void deleteEmployee(int id) {
        System.out.println(employees.size());
        employeeService.deleteEmployee(id);
        loadEmployees();
    }



    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    // Validators
    public void validateAge(FacesContext ctx, UIComponent comp, Object value) {
        Object validatorMethod = comp.getAttributes().get("validatorMethod");

        int age = (Integer) value;
        if (age < 18 || age > 65) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Age must be between 18 and 65 years", null);
            throw new ValidatorException(message);
        }
    }


    public void validateDate(FacesContext ctx, UIComponent comp, Object value) {
        Object validatorMethod = comp.getAttributes().get("validatorMethod");

        try {
            LocalDate dob = (LocalDate) value;
            LocalDate currentDate = LocalDate.now();
            if (dob.isAfter(currentDate)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date of Birth cannot be in the future", null));
            }
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Date format. Use yyyy-MM-dd", null));
        }
    }


    public void validateName(FacesContext ctx, UIComponent comp, Object value) {
        Object validatorMethod = comp.getAttributes().get("validatorMethod");

        String name = value.toString().trim();
        if (name.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name cannot be empty", null));
        }
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must contain only letters and spaces", null));
        }
    }

}
