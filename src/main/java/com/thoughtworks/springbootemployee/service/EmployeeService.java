package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public Employee updateEmployeeById(int employeeId, Employee newEmployee) {
        Employee updateEmployee = employeeRepository.findById(employeeId).orElse(null);
        if (updateEmployee!=null&&newEmployee!=null){

            updateEmployee.setAge(newEmployee.getAge());
            updateEmployee.setGender(newEmployee.getGender());
            updateEmployee.setName(newEmployee.getName());

        }
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
       return employeeRepository.findAll(PageRequest.of(page,pageSize)).toList();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
         this.employeeRepository.delete(employee);
    }
}
