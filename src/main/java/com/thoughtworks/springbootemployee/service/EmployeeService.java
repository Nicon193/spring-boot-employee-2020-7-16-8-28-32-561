package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public Employee updateEmployeeById(int employeeId, Employee newEmployee) {
        Employee updateEmployee = employeeRepository.getOne(employeeId);
        if (updateEmployee!=null&&newEmployee!=null){

            updateEmployee.setAge(newEmployee.getAge());
            updateEmployee.setGender(newEmployee.getGender());
            updateEmployee.setName(newEmployee.getName());

        }
        employeeRepository.save(updateEmployee);
        return updateEmployee;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByID(int id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> getRangeOfEmployees(int page, int pageSize) {
       return employeeRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(int employeeId) {
       Employee deleteEmployee  = employeeRepository.getOne(employeeId);
        employeeRepository.delete(deleteEmployee);
        return deleteEmployee;
    }
}
