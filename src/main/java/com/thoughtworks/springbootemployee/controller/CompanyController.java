package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.ConpaniesInitialization;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeesInitialization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping()
    public List<Company> getCompanyList(){
        ConpaniesInitialization conpaniesInitialization =new ConpaniesInitialization();
        List<Company> companies =conpaniesInitialization.returnCompanies();
        return companies;
    }

    @GetMapping(path = "/{Id}")
    public Company getCompanyByNumber(@PathVariable int Id) {

        ConpaniesInitialization conpaniesInitialization =new ConpaniesInitialization();
        List<Company> companies =conpaniesInitialization.returnCompanies();
        if(Id>=0&&Id<companies.size()){
            return  companies.get(Id);
        }
        return null;
    }
    @GetMapping(path = "/{Id}/employees")
    public List<Employee> getCompanyEmployeessByNumber(@PathVariable int Id) {

        ConpaniesInitialization conpaniesInitialization =new ConpaniesInitialization();
        List<Company> companies =conpaniesInitialization.returnCompanies();
        if(Id>=0&&Id<companies.size()){
            return  companies.get(Id).getEmployees();
        }
        return null;
    }
}
