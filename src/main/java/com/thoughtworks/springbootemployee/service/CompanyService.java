package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.ErrorOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
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

    public List<Company> findAll() {
        return companyRespository.findAll();
    }

    public Company findCompanyByID(int id) {
        Company company = this.companyRespository.findById(id).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }

        return company;
    }

    public List<Employee> findCompanyEmployeesByID(int companyID) {

        Company company = companyRespository.findById(companyID).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }
        List<Employee> employees = company.getEmployees();
        if (employees == null) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    public List<Company> findRangeOfCompany(int page, int pageSize) {
        return this.companyRespository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, pageSize)).getContent();
    }

    public Company addCompany(Company company) {
        return companyRespository.save(company);
    }

    public Company deleteCompany(int companyID) {
        Company company = findCompanyByID(companyID);
        if (company == null) {
            throw new ErrorOperationException();
        }
        companyRespository.deleteById(companyID);
        return company;
    }

    public Company update(int id, Company company) {
        Company updateCompany = findCompanyByID(id);
        if (updateCompany == null) {
            throw new NoSuchDataException();
        }
        if (company != null) {
            if (company.getCompanyName() != null)
                updateCompany.setCompanyName(company.getCompanyName());
            if (company.getEmployeesNumber() != null)
                updateCompany.setEmployeesNumber(company.getEmployeesNumber());
            if (company.getEmployees() != null)
                updateCompany.setEmployees(company.getEmployees());
        }
        companyRespository.save(updateCompany);
        return updateCompany;
    }
}
