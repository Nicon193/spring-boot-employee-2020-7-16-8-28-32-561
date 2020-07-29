package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    @Test
    void should_update_employee_when_update_employee_by_id_given_employee_information() {
        //given
        EmployeeService employeeService = new EmployeeService();

        //when
        Employee updateEmployee = employeeService.update(1, new Employee(1, "test", 18, "male"));


        //then
        assertEquals(1, updateEmployee.getId());
        assertEquals("test",updateEmployee.getName());
        assertEquals(18,updateEmployee.getAge());
        assertEquals("male",updateEmployee.getGender());

    }
}
