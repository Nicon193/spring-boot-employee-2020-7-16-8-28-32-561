package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company  {
    private int   id;
    private String companyName;
    private String employeesNumber;
    private List<Employee> employees;

    public Company(int id, String companyName, String employeesNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(String employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void  updateCompany(String companyName, String employeesNumber, List<Employee> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }
}
