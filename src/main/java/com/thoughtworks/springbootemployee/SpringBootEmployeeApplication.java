package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootEmployeeApplication {
	public static  List<Employee> employees = new ArrayList<>();

	public static void main(String[] args) {
		employeesinitialization(employees);
		SpringApplication.run(SpringBootEmployeeApplication.class, args);
	}



	public static void employeesinitialization(List<Employee> employees){
		SpringBootEmployeeApplication.employees.add(new Employee(0, "Xiaoming", 20, "Male"));
		SpringBootEmployeeApplication.employees.add(new Employee(1, "Xiaohong", 19, "Female"));
		SpringBootEmployeeApplication.employees.add(new Employee(2, "Xiaozhi", 15, "Male"));
		SpringBootEmployeeApplication.employees.add(new Employee(3, "Xiaogang", 16, "Male"));
		SpringBootEmployeeApplication.employees.add(new Employee(4, "Xiaoxia", 15, "Female"));

	}

}
