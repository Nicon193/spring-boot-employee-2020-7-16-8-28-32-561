package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.ConpaniesInitialization;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeesInitialization;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {


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

    @GetMapping()
    public List<Company> getCompanyList(@RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        ConpaniesInitialization conpaniesInitialization =new ConpaniesInitialization();
        List<Company> companies =conpaniesInitialization.returnCompanies();
        if (page != null && pageSize != null) {
            return companies.subList(--page, --pageSize);
        }

        return companies;
    }
}
