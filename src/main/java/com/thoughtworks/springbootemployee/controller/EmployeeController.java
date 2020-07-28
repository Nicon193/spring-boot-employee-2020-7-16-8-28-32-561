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

    @GetMapping(path = "/{number}")
    public Employee getEmployeeByNumber(@PathVariable String number) {
         return employees.get(Integer.parseInt(number));

    }




}
