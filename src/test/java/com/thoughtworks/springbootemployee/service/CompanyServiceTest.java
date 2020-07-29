package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {

    @Test
    void should_return_company_list_when_find_company_information_given_null() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findAll()).willReturn(Collections.singletonList(new Company(1, "alibaba", "50", null)));
        //when
        List<Company> companyList =CompanyService.findAll();
        //then

        assertNotNull(companyList);
    }

    @Test
    void should_return_certain_company_when_find_company_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findCompanyByID()).willReturn(new Company(1, "alibaba", "50", null));
        //when
        Company company =CompanyService.findCompanyByID();
        //then

        assertNotNull(company);

    }
}
