package com.company_rest_full.controller;

import com.company_rest_full.entity.Address;
import com.company_rest_full.entity.Company;
import com.company_rest_full.payload.AddressDto;
import com.company_rest_full.payload.CompanyDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<Result> addCompany(@Valid @RequestBody CompanyDto companyDto){
        return companyService.addCompany(companyDto);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCompany(@PathVariable Long id){
        return  ResponseEntity.ok(companyService.getCompany(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyService.getAllCompany());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deletecompany(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Result> editAddress(@Valid @RequestBody CompanyDto companyDto,@PathVariable Long id){
        Result result = companyService.editCompamy(companyDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
