package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {

    @Test
    void should_return_company_list_when_find_company_information_given_null() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findAll()).willReturn(Collections.singletonList(new Company(1, "alibaba", "50", null)));
        //when
        List<Company> companyList =companyService.findAll();
        //then

        assertEquals(1,companyList.get(0).getId());
        assertEquals("alibaba",companyList.get(0).getCompanyName());
        assertEquals("50",companyList.get(0).getEmployeesNumber());
    }

    @Test
    void should_return_certain_company_when_find_company_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findById(1)).willReturn(java.util.Optional.of(new Company(1, "alibaba", "50", null)));
        //when
        Company company =companyService.findCompanyByID(1);
        //then

        assertEquals(1,company.getId());
        assertEquals("alibaba",company.getCompanyName());
        assertEquals("50",company.getEmployeesNumber());
    }

    @Test
    void should_return_company_employee_list_when_find_company_employee_list_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRespository);

        given(mockedCompanyRespository.findEmployeesById(2)).willReturn(Arrays.asList(new Employee(1, "alibaba", 50, "female") ,new Employee(2, "tengxun", 40, "male")));
        //when
        List<Employee> employees =companyService.findCompanyEmployeesByID(2);
        //then

        assertEquals(1,employees.get(0).getId());
        assertEquals("alibaba",employees.get(0).getName());
        assertEquals(50,employees.get(0).getAge());
    }

    @Test
    void should_return_range_of_company_when_find_range_of_company_given_page_and_page_size() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRespository);
        given(mockedCompanyRespository.findAll(PageRequest.of(3,3))).willReturn((new PageImpl<>(Arrays.asList(
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>()),
                new Company(1, "OOCL", "0", new ArrayList<>())
        ))));

        //when
        List<Company> companies = companyService.findRangeOfCompany(3, 3);

        //then
        assertEquals(1,companies.get(0).getId());
        assertEquals("OOCL",companies.get(0).getCompanyName());
        assertEquals("0",companies.get(0).getEmployeesNumber());

    }

    @Test
    void should_return_company_when_add_company_given_company() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRespository);
        Company company =new Company(1, "alibaba", "50", null);
        given(mockedCompanyRespository.save(company)).willReturn(company);
        //when
        Company addCompany =companyService.addCompany(company);
        //then

        assertEquals(company.getId(),addCompany.getId());
        assertEquals(company.getCompanyName(),addCompany.getCompanyName());
        assertEquals(company.getEmployeesNumber(),addCompany.getEmployeesNumber());
    }


    @Test
    void should_return_update_compeny_when_update_company_given_company() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRepository);
        given(mockedCompanyRepository.getOne(1)).willReturn(new Company(1, "OOCL", "0", new ArrayList<>()));

        //when
        Company updateCompany = companyService.update(1, new Company(1, "OOIL", "0", new ArrayList<>()));

        //then
        assertEquals("OOIL",updateCompany.getCompanyName());

    }

    @Test
    void should_return_company_when_delete_company_given_company_id() {
        //given
        CompanyRepository mockedCompanyRespository = mock(CompanyRepository.class);
        CompanyService CompanyService = new CompanyService(mockedCompanyRespository);
        given(mockedCompanyRespository.findById(1)).willReturn(java.util.Optional.of(new Company(1, "OOCL", "0", new ArrayList<>())));
        mockedCompanyRespository.deleteById(1);
        //when
        Company company =CompanyService.deleteCompany(1);
        //then

        assertEquals(1,company.getId());
    }
}
