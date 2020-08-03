package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeDTO;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping()
    public List<EmployeeDTO> getEmployeeList(@RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                             @RequestParam(name = "gender", required = false) String gender) {
        if (page!=null && pageSize!=null){
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            List<Employee> employees= employeeService.getRangeOfEmployees(page,pageSize);


            return employeeMapper.switchEntityListToDtoList(employees,employeeDTOList);
        }

        if (gender!=null){
           return employeeMapper.switchEntityListToDtoList(employeeService.findEmployeeByGender(gender),new ArrayList<>());

        }

        return employeeMapper.switchEntityListToDtoList(employeeService.findAll(),new ArrayList<>());
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeByNumber(@PathVariable int employeeId) {
        return employeeMapper.switchEntityToDto(employeeService.findEmployeeByID(employeeId));

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        Employee savedEmployee = employeeService.addEmployee(employeeMapper.switchDtoToEntity(employee));
        return employeeMapper.switchEntityToDto(savedEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO updateEmployeeById(@PathVariable Integer employeeId,@RequestBody EmployeeDTO employee) {
        Employee updateEmployee  = employeeService.updateEmployeeById(employeeId,employeeMapper.switchDtoToEntity(employee));

        return employeeMapper.switchEntityToDto(updateEmployee);
    }

    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeDTO deleteEmployeeById(@PathVariable Integer employeeId) {
       return employeeMapper.switchEntityToDto(employeeService.deleteEmployee(employeeId));
    }

}
