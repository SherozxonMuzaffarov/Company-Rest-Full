package com.company_rest_full.service;

import com.company_rest_full.entity.Address;
import com.company_rest_full.entity.Company;
import com.company_rest_full.payload.AddressDto;
import com.company_rest_full.payload.CompanyDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.repository.AddressRepository;
import com.company_rest_full.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity addCompany(CompanyDto companyDto) {

        boolean company = companyRepository.existsCompanyByCompanyName(companyDto.getCompanyName());
        if (company)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Company  saved before with this Company name", false));

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Address  not found", false));

        Company company1 = new Company();
        company1.setCompanyName(companyDto.getCompanyName());
        company1.setCompanyDirector(companyDto.getCompanyDirector());
        company1.setAddress(optionalAddress.get());
        companyRepository.save(company1);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Result("Company saved successfully", true));

    }

    public Object getCompany(Long id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Company not found with this Id:" + id, false);

        return optionalCompany.get();
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Result deletecompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Company not found with this Id:" + id, false);

        companyRepository.deleteById(id);
        return  new Result("Company deleted successfully", true);
    }

    public Result editCompamy(CompanyDto companyDto, Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Addres not found with this Id:" + id, false);

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Address  not found", false);


        Company company = optionalCompany.get();
        company.setAddress(optionalAddress.get());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyDirector(companyDto.getCompanyDirector());

        companyRepository.save(company);
        return new Result("Company saved successfully", true);

    }
}
