package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRespository;

    public CompanyService(CompanyRepository companyRespository) {
        this.companyRespository = companyRespository;
    }

    public List<Company> findAll(){
        return  companyRespository.findAll();
    }

    public Company findCompanyByID(int id) {
        return this.companyRespository.findById(id).orElse(null);
    }

    public List<Employee> findCompanyEmployeesByID(int companyID) {

        List<Employee> employees = companyRespository.findEmployeesById(companyID);
        return  employees;
    }

    public List<Company> findRangeOfCompany(int page, int pageSize) {
        return this.companyRespository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    public Company addCompany(Company company) {
        return  companyRespository.save(company);
    }

    public Company deleteCompany(int companyID) {
        Company company = findCompanyByID(companyID);
        if(company!=null){
            companyRespository.deleteById(companyID);
        }
        return company;
    }

    public Company update(int id, Company company) {
        Company updateCompany = this.companyRespository.getOne(id);
        if(updateCompany!=null&&company!=null) {
            updateCompany.setCompanyName(company.getCompanyName());
            updateCompany.setEmployees(company.getEmployees());
            updateCompany.setEmployeesNumber(company.getEmployeesNumber());
        }
        this.companyRespository.save(updateCompany);
        return updateCompany;
    }
}
