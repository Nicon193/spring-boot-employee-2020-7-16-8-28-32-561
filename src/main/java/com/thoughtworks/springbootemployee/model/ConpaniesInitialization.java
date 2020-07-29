package com.thoughtworks.springbootemployee.model;


import java.util.ArrayList;
import java.util.List;

public class ConpaniesInitialization {
public List<Company> companies =new ArrayList<>();


    public ConpaniesInitialization() {
        EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
        List<Employee> employees = EmployeesInitialization.retrunEmployees();
        companies.add(new Company(0,"libaba","5",employees));
        companies.add(new Company(1,"paidu","5",employees));
        companies.add(new Company(2,"tencet","5",employees));
    }

    public List<Company> returnCompanies(){
        return  companies;
    }
}
