package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void should_return_company_employee_list_when_find_company_employee_list_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findCompanyEmployeesByID(2)).willReturn(Arrays.asList(new Employee(1, "alibaba", 50, "female") ,new Employee(2, "tengxun", 40, "male")));
        //when
        List<Employee> employees =CompanyService.findCompanyEmployeesByID(2);
        //then

        assertNotNull(employees);
    }

    @Test
    void should_return_range_of_company_when_find_range_of_company_given_page_and_page_size() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService employeeService = new CompanyService(mockedCompanyRespository);
        given(mockedCompanyRespository.findRangeOfCompany(3, 3)).willReturn(Arrays.asList(
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>())
        ));

        //when
        List<Company> companies = employeeService.findRangeOfCompany(3, 3);

        //then
        assertNotNull(companies);

    }
}
