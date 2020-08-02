package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getEmployeeList(@RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                          @RequestParam(name = "gender", required = false) String gender) {
        if (page!=null && pageSize!=null){
            return employeeService.getRangeOfEmployees(page,pageSize);
        }

        if (gender!=null){
            return employeeService.findEmployeeByGender(gender);
        }

        return employeeService.findAll();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeByNumber(@PathVariable int employeeId) {
        return employeeService.findEmployeeByID(employeeId);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    @PutMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployeeById(@PathVariable Integer employeeId,@RequestBody Employee employee) {
        return employeeService.updateEmployeeById(employeeId,employee);
    }

    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee deleteEmployeeById(@PathVariable Integer employeeId) {
       return employeeService.deleteEmployee(employeeId);
    }

}
