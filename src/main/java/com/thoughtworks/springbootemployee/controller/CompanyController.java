package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.ConpaniesInitialization;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {
    ConpaniesInitialization conpaniesInitialization = new ConpaniesInitialization();
    List<Company> companies = conpaniesInitialization.returnCompanies();

    @GetMapping(path = "/{Id}")
    public Company getCompanyByNumber(@PathVariable int Id) {


        return companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);

    }

    @GetMapping(path = "/{Id}/employees")
    public List<Employee> getCompanyEmployeessByNumber(@PathVariable int Id) {

        Company company = companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);
        if (company != null) {
            return company.getEmployees();
        }
        return null;
    }

    @GetMapping()
    public List<Company> getCompanyList(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        if (page != null && pageSize != null) {
            return companies.subList(--page * pageSize, page * pageSize - 1);
        }
        return companies;
    }

    @ResponseBody
    @PostMapping
    public Company addCompany(@RequestBody Company company) {

        if (company != null) {
            companies.add(company);
            return company;
        }
        return company;
    }

    @PutMapping(path = "/{Id}")
    public Company updateCompanyById(
            @PathVariable int Id,
            @RequestParam(name = "companyName", required = false) String companyName,
            @RequestParam(name = "employeesNumber", required = false) String employeesNumber,
            @RequestParam(name = "employees", required = false) List<Employee> employees

    ) {
        Company company = companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);
        if (company != null) {

            company.updateCompany(companyName, employeesNumber, employees);
            return company;
        }

        return company;
    }

    @DeleteMapping(path = "/{Id}")
    public Company deleteCompanyById(@PathVariable int Id) {
        Company company = companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);
        if (company != null) {
            companies.remove(company);
            return company;
        }
        return company;
    }

}
