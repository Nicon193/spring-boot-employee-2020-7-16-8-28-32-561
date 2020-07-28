package com.thoughtworks.springbootemployee.model;


import java.util.ArrayList;
import java.util.List;

public class ConpaniesInitialization {
public List<Company> companies =new ArrayList<>();


    public ConpaniesInitialization() {
        EmployeesInitialization EmployeesInitialization = new EmployeesInitialization();
        List<Employee> employees = EmployeesInitialization.retrunEmployees();
        companies.add(new Company("libaba","5",employees));
        companies.add(new Company("paidu","5",employees));
        companies.add(new Company("tencet","5",employees));
    }

    public List<Company> returnCompanies(){
        return  companies;
    }
}
