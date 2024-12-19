package com.example.cruddemo.bean;

import com.example.cruddemo.model.Employee;
import com.example.cruddemo.service.EmployeeService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class EmployeeSearchBean implements Serializable {

    private Integer employeeId;
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        System.out.println("EmployeeSearchBean @PostConstruct: Bean initialized.");
        if (conversation.isTransient()) {
            conversation.begin();
            System.out.println("Conversation started. ID: " + conversation.getId());
        }
    }

    public String searchEmployee() {
        if (employeeId != null) {
            employee = employeeService.getEmployeeById(employeeId);
            System.out.println("Employee found: " + (employee != null ? employee.getEmployeeName() : "Not Found"));
        }
        return null;
    }

    public String endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
            System.out.println("Conversation ended. ID: " + conversation.getId());
        }
        return "index?faces-redirect=true"; // Quay lại trang index
    }

    @PreDestroy
    public void destroy() {
        System.out.println("EmployeeSearchBean @PreDestroy: Bean destroyed.");
        System.out.println("------------------------");
    }

    // Getters và Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }
}

