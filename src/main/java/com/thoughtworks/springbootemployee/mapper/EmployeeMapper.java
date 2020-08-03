package com.thoughtworks.springbootemployee.mapper;


import com.thoughtworks.springbootemployee.dto.EmployeeDTO;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public EmployeeDTO switchEntityToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public Employee switchDtoToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }


    public List<EmployeeDTO> switchEntityListToDtoList(List<Employee>employees,List<EmployeeDTO>employeeDTOList){
        for (Employee employee: employees
             ) {
            employeeDTOList.add(switchEntityToDto(employee));
        }
        return employeeDTOList;
    }

}
