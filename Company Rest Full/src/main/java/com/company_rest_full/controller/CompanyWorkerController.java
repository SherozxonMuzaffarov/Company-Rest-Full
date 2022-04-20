package com.company_rest_full.controller;

import com.company_rest_full.entity.CompanyWorker;
import com.company_rest_full.entity.Department;
import com.company_rest_full.payload.CompanyWorkerDto;
import com.company_rest_full.payload.DepartmentDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.service.CompanyWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class CompanyWorkerController {

    @Autowired
    CompanyWorkerService workerService;

    @PostMapping("/add")
    public ResponseEntity<Result> addWorker(@RequestBody CompanyWorkerDto companyWorkerDto){
        return workerService.addWorker(companyWorkerDto);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getWorker(@PathVariable Long id){
        return  workerService.getWorker(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyWorker>> getAllWorker(){
        return workerService.getAllWorker();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteWorker(@PathVariable Long id){
        return workerService.deleteWorker(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Result> editWorker(@PathVariable Long id, @RequestBody CompanyWorkerDto companyWorkerDto){
        return workerService.editWorker(id, companyWorkerDto);
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
