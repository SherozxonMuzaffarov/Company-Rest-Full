package com.company_rest_full.service;

import com.company_rest_full.entity.Address;
import com.company_rest_full.entity.Company;
import com.company_rest_full.entity.CompanyWorker;
import com.company_rest_full.entity.Department;
import com.company_rest_full.payload.CompanyWorkerDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.repository.AddressRepository;
import com.company_rest_full.repository.CompanyWorkerRepository;
import com.company_rest_full.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyWorkerService {

    @Autowired
    CompanyWorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<Result> addWorker(CompanyWorkerDto companyWorkerDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(companyWorkerDto.getDepartmentID());
        if (!optionalDepartment.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Department not found with this ID:" + companyWorkerDto.getDepartmentID(),false));

        Optional<Address> optionalAddress = addressRepository.findById(companyWorkerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Address not found with this ID:" + companyWorkerDto.getAddressId(),false));

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(companyWorkerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Worker saved with this phone number:" + companyWorkerDto.getPhoneNumber(),false));

        CompanyWorker companyWorker = new CompanyWorker();
        companyWorker.setName(companyWorkerDto.getName());
        companyWorker.setPhoneNumber(companyWorkerDto.getPhoneNumber());
        companyWorker.setAddress(optionalAddress.get());
        companyWorker.setDepartment(optionalDepartment.get());

        workerRepository.save(companyWorker);

        return  ResponseEntity.status(HttpStatus.CREATED).body(new Result("New worker saved successfully",true));

    }

    public ResponseEntity<?> getWorker(Long id) {
        Optional<CompanyWorker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Worker not found with this ID:" + id,false));

        return  ResponseEntity.ok(optionalWorker.get());
    }

    public ResponseEntity<List<CompanyWorker>> getAllWorker() {
        return ResponseEntity.ok(workerRepository.findAll());
    }

    public ResponseEntity<Result> deleteWorker(Long id) {
        Optional<CompanyWorker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Worker not found with this ID:" + id,false));

        workerRepository.deleteById(id);
        return  ResponseEntity.ok(new Result("Worker deleted successfully", true));
    }

    public ResponseEntity<Result> editWorker(Long id, CompanyWorkerDto companyWorkerDto) {
        Optional<CompanyWorker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Worker not found with this ID:" + id,false));

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(companyWorkerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Worker saved with this phone number:" + companyWorkerDto.getPhoneNumber(),false));

        Optional<Department> optionalDepartment = departmentRepository.findById(companyWorkerDto.getDepartmentID());
        if(!optionalDepartment.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Department not found with this ID:" + companyWorkerDto.getDepartmentID(),false));

        Optional<Address> optionalAddress = addressRepository.findById(companyWorkerDto.getAddressId());
        if(!optionalAddress.isPresent())
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Address not found with this ID:" + companyWorkerDto.getAddressId(),false));

        CompanyWorker worker = new CompanyWorker();
        worker.setName(companyWorkerDto.getName());
        worker.setPhoneNumber(companyWorkerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(optionalAddress.get());
        workerRepository.save(worker);

        return  ResponseEntity.ok(new Result("Worker successfully edited",true));

    }
}
