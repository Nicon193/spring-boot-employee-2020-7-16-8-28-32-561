package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

public class EmployeeService {


    public Employee update(int employeeId, Employee newEmployee) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee updateEmployee = employeeRepository.findEmployeeByID(employeeId);
        updateEmployee.setName(newEmployee.getName());
        updateEmployee.setGender(newEmployee.getGender());
        updateEmployee.setAge(newEmployee.getAge());

        return updateEmployee;

    }
}
