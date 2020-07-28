package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.SpringBootEmployeeApplication;

import java.util.ArrayList;
import java.util.List;

public class EmployeesInitialization {
    public static List<Employee> employees = new ArrayList<>();

    public EmployeesInitialization() {
        employees.add(new Employee(0, "Xiaoming", 20, "Male"));
        employees.add(new Employee(1, "Xiaohong", 19, "Female"));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male"));
        employees.add(new Employee(3, "Xiaogang", 16, "Male"));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female"));
    }

    public static List<Employee> retrunEmployees(){
        return  employees;
    }

}
