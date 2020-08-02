package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @GetMapping
    public List<Company> getCompanyList(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "pageSize", required = false) Integer pageSize) {

       if (page!=null&&pageSize!=null)
       return companyService.findRangeOfCompany(page,pageSize);

       return companyService.findAll();
    }

    @GetMapping(path = "/{Id}")
    public Company getCompanyByNumber(@PathVariable int Id) {


       return companyService.findCompanyByID(Id);

    }

    @GetMapping(path = "/{Id}/employees")
    public List<Employee> getCompanyEmployeessByNumber(@PathVariable int Id) {

        return companyService.findCompanyEmployeesByID(Id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping(path = "/{Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Company updateCompanyById(
            @PathVariable int Id,
             @RequestBody Company newCompany
    ) {
      return companyService.update(Id,newCompany);
    }

    @DeleteMapping(path = "/{Id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company deleteCompanyById(@PathVariable int Id) {

       return companyService.deleteCompany(Id);
    }

}
