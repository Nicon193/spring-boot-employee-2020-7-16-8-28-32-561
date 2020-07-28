package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeesInitialization;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @GetMapping()
    public List<Employee> getEmployeeList(@RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize, @RequestParam(name = "gender", required = false) String gender) {
        EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
        List<Employee> employees = EmployeesInitialization.retrunEmployees();
        if(page!=null&&pageSize!=null){
            return employees.subList(--page,--pageSize);
        }
        if (gender!=null){
           return employees.stream().filter(employee -> employee.getGender()==gender).collect(Collectors.toList());

        }

        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeByNumber(@PathVariable String employeeId) {

        EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
        List<Employee> employees = EmployeesInitialization.retrunEmployees();
        return employees.stream().filter(employee -> employee.getId() == Integer.parseInt(employeeId)).findFirst().orElse(null);


    }


}
