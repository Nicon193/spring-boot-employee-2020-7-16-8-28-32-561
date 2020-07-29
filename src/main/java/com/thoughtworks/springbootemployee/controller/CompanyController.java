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
    ConpaniesInitialization conpaniesInitialization = new ConpaniesInitialization();
    List<Company> companies = conpaniesInitialization.returnCompanies();

    @GetMapping(path = "/{Id}")
    public Company getCompanyByNumber(@PathVariable int Id) {


        Company company =companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);
        if (company!=null){
            return company;
        }
        return null;
    }

    @GetMapping(path = "/{Id}/employees")
    public List<Employee> getCompanyEmployeessByNumber(@PathVariable int Id) {

        Company company =companies.stream().filter(companie -> companie.getId() == Id).findFirst().orElse(null);
        if (company!=null){
            return company.getEmployees();
        }
        return null;
    }

    @GetMapping()
    public List<Company> getCompanyList(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        if (page != null && pageSize != null) {
            return companies.subList(--page, --pageSize);
        }
        return companies;
    }

    @ResponseBody
    @PostMapping
    public String addCompany(@RequestBody Company company) {

        if (company != null) {
            companies.add(company);
            return "Added successfully";
        }
        return "Add failed";
    }

    @PutMapping(path = "/{Id}")
    public String updateCompany(
            @PathVariable int Id,
            @RequestParam(name = "companyName", required = false) String companyName,
            @RequestParam(name = "employeesNumber", required = false) String employeesNumber,
            @RequestParam(name = "employees", required = false) List<Employee> employees

    ) {

        if (Id>=0&&Id<companies.size()){
            companies.get(Id).updateCompany(companyName,employeesNumber,employees);
            return "Update successfully";
        }

        return "Update failed";
    }

    @DeleteMapping(path = "/{Id}")
    public String deleteCompany(   @PathVariable int Id){
        if (Id>=0&&Id<companies.size()){
            companies.remove(Id);
            return "delete successfully";
        }

        return "delete failed";
    }

}
