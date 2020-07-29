package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeesInitialization;

import java.util.List;

public class EmployeeRepository {
    EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
    List<Employee> employees = EmployeesInitialization.retrunEmployees();


    public Employee findEmployeeByID(int employeeId) {
        return employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().orElse(null);
    }

}
