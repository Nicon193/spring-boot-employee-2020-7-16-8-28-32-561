package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {


    public EmployeeService(EmployeeRepository employeeRepository) {

    }

    public Employee update(int employeeId, Employee newEmployee) {


        return new Employee(1, "ffff", 19, "female");

    }

    public List<Employee> findAll() {
        return null;
    }
}
