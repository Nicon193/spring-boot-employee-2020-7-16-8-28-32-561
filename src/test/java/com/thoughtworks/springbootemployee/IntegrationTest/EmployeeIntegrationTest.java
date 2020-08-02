package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void afterEach() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();

    }

    @Test
    void should_return_employee_list_when_hit_get_EmployeesOfGender_given_Gender() throws Exception {
        //given
        Employee firstEmployee = new Employee(3, "alibaba3", 20, "male");
        Employee secondEmployee = new Employee(4, "alibaba4", 21, "male");
        Employee thirdEmployee = new Employee(5, "alibaba5", 21, "female");
        employeeRepository.save(firstEmployee);
        employeeRepository.save(secondEmployee);
        employeeRepository.save(thirdEmployee);

        mockMvc.perform(get("/employees?gender=male"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("alibaba3"))
                .andExpect(jsonPath("$[1].name").value("alibaba4"));
    }



    @Test
    void should_return_employees_when_hit_get_employees_given_null() throws Exception {
        //given
        Employee employee = new Employee(3, "alibaba1", 20, "male");
        Company company = new Company(1, "alibaba", "1", Collections.emptyList());
        Company newCompany = companyRepository.save(company);
        employee.setCompanyId(newCompany.getId());
        employeeRepository.save(employee);

        mockMvc.perform(get("/employees"))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].companyId").value(newCompany.getId()));
    }

    @Test
    void should_return_certain_employee_when_hit_get_employee_by_id_given_id() throws Exception {
        //given
        Employee employee = new Employee(3, "alibaba1", 20, "male");
        Company company = new Company(1, "alibaba", "1", Collections.emptyList());
        Company newCompany = companyRepository.save(company);
        employee.setCompanyId(newCompany.getId());
        Employee newEmployee = employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees/" + newEmployee.getId()))
                .andExpect(jsonPath("$.id").value(newEmployee.getId()))
                .andExpect(jsonPath("$.name").value(newEmployee.getName()))
                .andExpect(jsonPath("$.age").value(newEmployee.getAge()))
                .andExpect(jsonPath("$.gender").value(newEmployee.getGender()))
                .andExpect(jsonPath("$.companyId").value(newCompany.getId()));
    }

    @Test
    void should_insert_employee_when_hit_post_employee_given_employee() throws Exception {
        //given
        String employeeJson = "{\n" +
                "    \"id\": 1, \n" +
                "    \"name\": \"Xiaoxia\", \n" +
                "    \"age\": 15, \n" +
                "    \"gender\": \"Female\"\n" +
                "}";

        mockMvc.perform(post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Xiaoxia"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.gender").value("Female"));
    }

    @Test
    void should_return_employee_list_when_hit_get_EmployeesPagination_given_page_pageSize() throws Exception {
        //given
        Employee firstEmployee = new Employee(3, "alibaba3", 20, "male");
        Employee secondEmployee = new Employee(4, "alibaba4", 21, "male");
        employeeRepository.save(firstEmployee);
        employeeRepository.save(secondEmployee);


        mockMvc.perform(get("/employees?page=1&pageSize=2"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("alibaba3"))
                .andExpect(jsonPath("$[1].name").value("alibaba4"));
    }

    @Test
    void should_return_newEmployee_when_hit_push_employee_id_given_id_employee() throws Exception {
        //given
        Employee firstEmployee = new Employee(3, "alibaba3", 20, "male");
        Employee employee = employeeRepository.save(firstEmployee);

        String employeeJson = "{\n" +
                "    \"id\": 3, \n" +
                "    \"name\": \"Xiaoxia\", \n" +
                "    \"age\": 15, \n" +
                "    \"gender\": \"Female\"\n" +
                "}";

        mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(jsonPath("$.name").value("Xiaoxia"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.gender").value("Female"));
    }

    @Test
    void should_return_employee_when_hit_delete_employees_id_given_employeeId() throws Exception {
        //given
        Employee firstEmployee = new Employee(3, "alibaba3", 20, "male");
        Employee savedEmployee = employeeRepository.save(firstEmployee);



        mockMvc.perform(delete("/employees/" + savedEmployee.getId()))
                .andExpect(jsonPath("$.id").value(savedEmployee.getId()))
                .andExpect(jsonPath("$.name").value("alibaba3"));
    }

}
