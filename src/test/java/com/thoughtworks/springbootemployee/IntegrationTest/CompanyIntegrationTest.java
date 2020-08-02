package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}
