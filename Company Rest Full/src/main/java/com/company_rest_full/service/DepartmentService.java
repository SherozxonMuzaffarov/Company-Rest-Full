package com.company_rest_full.service;

import com.company_rest_full.entity.Company;
import com.company_rest_full.entity.Department;
import com.company_rest_full.payload.DepartmentDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.repository.CompanyRepository;
import com.company_rest_full.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<Result> addDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Company not found with this ID:" + departmentDto.getCompanyId(),false));

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return  ResponseEntity.status(HttpStatus.CREATED).body(new Result("new Department saved successfully",true));
    }

    public ResponseEntity<?> getDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Department not found with this ID:" + id,false));

        return  ResponseEntity.ok(optionalDepartment.get());

    }

    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> all = departmentRepository.findAll();
        return ResponseEntity.ok(all);

    }

    public ResponseEntity<Result> deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Department not found with this ID:" + id,false));

        departmentRepository.deleteById(id);
        return  ResponseEntity.ok(new Result("Department deleted successfully", true));
    }

    public ResponseEntity<Result> editDepartment(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Department not found with this ID:" + id,false));

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if(!optionalCompany.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Company not found with this ID:" + departmentDto.getCompanyId(),false));

        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return  ResponseEntity.ok(new Result("Department successfully edited",true));
    }
}
