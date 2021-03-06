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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

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
    void should_return_companies_when_hit_get_companies_given_null() throws Exception {
        //given
        Company alibaba = new Company(1, "alibaba", "1", Collections.emptyList());
        Company tencent = new Company(2, "tencent", "1", Collections.emptyList());
        companyRepository.save(alibaba);
        companyRepository.save(tencent);


        mockMvc.perform(get("/companies"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].companyName").value(alibaba.getCompanyName()))
                .andExpect(jsonPath("$[1].companyName").value(tencent.getCompanyName()));
    }

    @Test
    void should_return_company_when_hit_get_companies_id_given_companyId() throws Exception {
        //given
        Company alibaba = new Company(1, "alibaba", "1", Collections.emptyList());
        Company tencent = new Company(2, "tencent", "1", Collections.emptyList());
        Company savedAlibaba = companyRepository.save(alibaba);
        companyRepository.save(tencent);


        mockMvc.perform(get("/companies/" +savedAlibaba.getId()))
                .andExpect(jsonPath("$.id").value(savedAlibaba.getId()))
                .andExpect(jsonPath("$.companyName").value(alibaba.getCompanyName()));
    }

    @Test
    void should_return_companies_when_hit_get_CompanyPagination_given_page_pageSize() throws Exception {
        //given
        Company alibaba = new Company(1, "alibaba", "1", Collections.emptyList());
        Company tencent = new Company(2, "tencent", "1", Collections.emptyList());
        companyRepository.save(alibaba);
        companyRepository.save(tencent);


        mockMvc.perform(get("/companies?page=1&pageSize=2"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].companyName").value(alibaba.getCompanyName()))
                .andExpect(jsonPath("$[1].companyName").value(tencent.getCompanyName()));
    }

    @Test
    void should_return_employees_when_hit_get_companies_id_employees_given_companyId() throws Exception {
        //given
        Employee employee = new Employee(3, "alibaba1", 20, "male",1);
        Company alibaba = new Company(1, "alibaba", "1", new ArrayList<>(Arrays.asList(employee)));
        Company tencent = new Company(2, "tencent", "1", Collections.emptyList());
        Company savedAlibaba = companyRepository.save(alibaba);
        companyRepository.save(tencent);


        mockMvc.perform(get("/companies/" +savedAlibaba.getId()+"/employees"))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("alibaba1"));
    }

    @Test
    void add_return_company_when_hit_post_companies_given_company() throws Exception {
        //given
        String companyJson ="{   \n" +
                "    \"id\":5,\n" +
                "    \"companyName\":\"alibaba\",\n" +
                "    \"employeesNumber\" : \"0\"\n" +
                "\n" +
                "}";
        //then
        mockMvc.perform(post("/companies/" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(jsonPath("$.employeesNumber").value(0))
                .andExpect(jsonPath("$.companyName").value("alibaba"));
    }

    @Test
    void should_delete_company_when_hit_delete_companies_id_given_companyId() throws Exception {
        //given
        Company alibaba = new Company(1, "alibaba", "1", Collections.emptyList());
        Company tencent = new Company(2, "tencent", "1", Collections.emptyList());
        Company savedAlibaba = companyRepository.save(alibaba);
        companyRepository.save(tencent);


        mockMvc.perform(delete("/companies/" +savedAlibaba.getId()))
                .andExpect(jsonPath("$.id").value(savedAlibaba.getId()))
                .andExpect(jsonPath("$.companyName").value(alibaba.getCompanyName()));
    }

    @Test
    void should_update_company_when_hit_push_companies_id_given_companyId_newNompany() throws Exception {
        //given

        Company alibaba = new Company(1, "alibaba", "1", Collections.emptyList());
        Company savedAlibaba = companyRepository.save(alibaba);
        String NewCompanyJson ="{   \n" +
                "    \"id\":1,\n" +
                "    \"companyName\":\"tencent\",\n" +
                "    \"employeesNumber\" : \"0\"\n" +
                "\n" +
                "}";
        //then
        mockMvc.perform(put("/companies/" +savedAlibaba.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(NewCompanyJson))
                .andExpect(jsonPath("$.companyName").value("tencent"));
    }

}
