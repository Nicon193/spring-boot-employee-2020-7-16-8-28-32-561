package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    @Test
    void should_update_employee_when_update_employee_by_id_given_employee_information() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        given(employeeService.findEmployeeByID(5)).willReturn(new Employee(3, "ffff", 18, "male"));

        //when
        Employee updateEmployee = employeeService.updateEmployeeById(5, new Employee(2, "test", 18, "male"));


        //then
        assertEquals("test",updateEmployee.getName());
        assertEquals(18,updateEmployee.getAge());
        assertEquals("male",updateEmployee.getGender());

    }

    @Test
    void should_all_employee_information_when_find_all_employee_information_given_null() {
        //given

        //when
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "ffff", 18, "male"));
        employeeList.add(new Employee(5, "ffff", 18, "male"));
        employeeList.add(new Employee(4, "ffff", 18, "male"));
        given(mockedEmployeeRespository.findAll()).willReturn(employeeList);

        //then

        assertNotNull(employeeService.findAll());
    }

    @Test
    void should_certain_employee_when_find_employee_by_id_given_id() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        Employee employee = new Employee(3, "ffff", 18, "male");
        given(mockedEmployeeRespository.findEmployeeByID(3)).willReturn(employee);

        //when
        Employee certainEmployee = employeeService.findEmployeeByID(3);

        //then
        assertEquals(employee, certainEmployee);
    }

    @Test
    void should_certain_gender_employee_when_find_employee_by_gender_given_gender() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "ffff", 18, "male"));
        employeeList.add(new Employee(5, "ffff", 18, "male"));
        given(mockedEmployeeRespository.findEmployeeByGender("male")).willReturn(employeeList);
        //when
        List<Employee> employees = employeeService.findEmployeeByGender("male");
        //then
        assertNotNull(employees);

    }

    @Test
    void should_range_of_employee_when_get_range_of_employees_given_page_and_page_size() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "ffff", 18, "male"));
        employeeList.add(new Employee(4, "ffff", 18, "male"));
        employeeList.add(new Employee(5, "ffff", 18, "male"));
        given(mockedEmployeeRespository.getRangeOfEmployees(3, 3)).willReturn(employeeList);

        //when
        List<Employee> employees = employeeService.getRangeOfEmployees(3, 3);

        //then
        assertEquals(employeeList, employees);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        Employee employee =new Employee(3, "ffff", 18, "male");

        given(mockedEmployeeRespository.addEmployee(employee)).willReturn(employee);

        //when
        Employee addedEmployee = employeeService.addEmployee(employee);

        //then
        assertEquals(employee, addedEmployee);
    }

    @Test
    void should_return_employee_when_delete_employee_given_employee() {
        //given
        EmployeeRepository mockedEmployeeRespository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRespository);
        Employee employee =new Employee(3, "ffff", 18, "male");

        given(mockedEmployeeRespository.deleteEmployee(employee)).willReturn(employee);

        //when
        Employee deletedEmployee = employeeService.deleteEmployee(employee);

        //then
        assertEquals(employee, deletedEmployee);
    }
}
