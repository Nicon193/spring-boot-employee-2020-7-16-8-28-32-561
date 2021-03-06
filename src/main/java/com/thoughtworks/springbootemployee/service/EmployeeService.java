package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.ErrorOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
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
        Employee updateEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (updateEmployee == null) {
            throw new ErrorOperationException();
        }
        if (newEmployee != null) {
            if (newEmployee.getName() != null)
                updateEmployee.setName(newEmployee.getName());
            if (newEmployee.getGender() != null)
                updateEmployee.setGender(newEmployee.getGender());
            if (newEmployee.getAge() != null)
                updateEmployee.setAge(newEmployee.getAge());
        }


        return    employeeRepository.save(updateEmployee);
    }

    public List<Employee> findAll() {
        List<Employee> employees=employeeRepository.findAll();


        return employees;
    }

    public Employee findEmployeeByID(int id) {

        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> getRangeOfEmployees(int page, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, pageSize)).getContent();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(int employeeId) {
        Employee deleteEmployee = employeeRepository.findById(employeeId).orElse(null);
        if (deleteEmployee==null){
            throw new NoSuchDataException();
        }
        employeeRepository.delete(deleteEmployee);
        return deleteEmployee;
    }
}
