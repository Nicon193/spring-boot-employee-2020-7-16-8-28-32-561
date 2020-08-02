package com.thoughtworks.springbootemployee.mapper;


import com.thoughtworks.springbootemployee.dto.CompanyDTO;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO switchEntityToDto(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company, companyDTO);
        return companyDTO;
    }

    public Company switchDtoToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);
        return company;
    }
}
