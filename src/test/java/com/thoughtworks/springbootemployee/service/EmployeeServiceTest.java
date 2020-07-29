package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    @Test
    void should_update_employee_when_update_employee_by_id_given_employee_information() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        given(mockedEmployeeRespository.findEmployeeByID(5)).willReturn(new Employee(3, "ffff", 18, "male"));

        //when
        Employee updateEmployee = employeeService.update(5, new Employee(2, "test", 18, "male"));


        //then
        assertEquals("test",updateEmployee.getName());
        assertEquals(18,updateEmployee.getAge());
        assertEquals("male",updateEmployee.getGender());

    }

    @Test
    void should_all_employee_information_when_find_all_employee_information_given_null() {
        //given


        //when

        //then

    }
}
