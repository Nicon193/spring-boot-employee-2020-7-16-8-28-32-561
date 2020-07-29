package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyRepository {

    public List<Company> findAll(){
        return null;
    }


    public Company findCompanyByID() {
        return null;
    }

    public List<Employee> findCompanyEmployeesByID(int companyID) {
        return null;
    }

    public List<Company> findRangeOfCompany(int page, int pageSize) {
        return null;
    }

    public Company addCompany() {
        return  null;
    }

    public Company deleteCompany(int companyID) {
        return null;
    }
}
