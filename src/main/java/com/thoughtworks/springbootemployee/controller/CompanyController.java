package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.ConpaniesInitialization;
import org.springframework.web.bind.annotation.GetMapping;
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
}
