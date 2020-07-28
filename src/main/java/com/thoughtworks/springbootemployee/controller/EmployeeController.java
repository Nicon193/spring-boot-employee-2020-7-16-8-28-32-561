package com.thoughtworks.springbootemployee.controller;



import com.thoughtworks.springbootemployee.SpringBootEmployeeApplication;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.springbootemployee.SpringBootEmployeeApplication.employees;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @GetMapping()
    public List<Employee> getEmployeeList() {
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeByNumber(@PathVariable String employeeId) {
         return employees.get(Integer.parseInt(employeeId));

    }




}
