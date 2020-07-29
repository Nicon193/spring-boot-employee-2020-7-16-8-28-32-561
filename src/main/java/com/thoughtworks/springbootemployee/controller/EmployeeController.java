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
    EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
    List<Employee> employees = EmployeesInitialization.retrunEmployees();

    @GetMapping()
    public List<Employee> getEmployeeList(@RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize, @RequestParam(name = "gender", required = false) String gender) {

        if (page != null && pageSize != null) {
            return employees.subList(--page * pageSize, page * pageSize - 1);
        }
        if (gender != null) {
            return employees.stream().filter(employee -> employee.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());

        }

        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeByNumber(@PathVariable String employeeId) {


        return employees.stream().filter(employee -> employee.getId() == Integer.parseInt(employeeId)).findFirst().orElse(null);

    }

    @PostMapping()
    public Employee addEmployee(@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "age", required = false) Integer age, @RequestParam(name = "gender", required = false) String gender) {
        Employee employee = new Employee(id, name, age, gender);


        employees.add(employee);
        return employee;
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployeeById(@PathVariable Integer employeeId, @RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "age", required = false) Integer age, @RequestParam(name = "gender", required = false) String gender) {

        Employee Employee = employees.stream().filter(employee -> employee.getId() == Integer.parseInt(String.valueOf(employeeId))).findFirst().orElse(null);
        if (Employee == null) {
            return null;
        }
        Employee.setName(name);
        Employee.setAge(age);
        Employee.setGender(gender);
        return Employee;

    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployeeById(@PathVariable Integer employeeId) {

        Employee Employee = employees.stream().filter(employee -> employee.getId() == Integer.parseInt(String.valueOf(employeeId))).findFirst().orElse(null);
        if (Employee == null) {
            return null;
        }
        employees.remove(Employee);
        return Employee;

    }

}
