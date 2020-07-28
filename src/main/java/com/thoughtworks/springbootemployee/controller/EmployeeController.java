package com.thoughtworks.springbootemployee.controller;



import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeesInitialization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @GetMapping()
    public List<Employee> getEmployeeList() {
        EmployeesInitialization  EmployeesInitialization = new EmployeesInitialization();
        return EmployeesInitialization.retrunEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeByNumber(@PathVariable String employeeId) {

        EmployeesInitialization  EmployeesInitialization = new EmployeesInitialization();
        List<Employee> employees =EmployeesInitialization.retrunEmployees();
        return employees.stream().filter(employee -> employee.getId()==Integer.parseInt(employeeId)).findFirst().orElse(null);



    }




}
