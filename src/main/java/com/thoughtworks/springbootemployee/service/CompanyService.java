package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {
    CompanyRepository companyRespository;

    public CompanyService(CompanyRepository companyRespository) {
        this.companyRespository = companyRespository;
    }

    public List<Company> findAll(){
        return  companyRespository.findAll();
    }

    public Company findCompanyByID() {
        return this.companyRespository.findCompanyByID();
    }

    public List<Employee> findCompanyEmployeesByID(int companyID) {
        return null;
    }
}