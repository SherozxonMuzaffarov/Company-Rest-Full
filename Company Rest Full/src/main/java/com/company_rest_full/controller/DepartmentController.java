package com.company_rest_full.controller;

import com.company_rest_full.entity.Department;
import com.company_rest_full.payload.DepartmentDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.service.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<Result> addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.addDepartment(departmentDto);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getDepartment(@PathVariable Long id){
        return  departmentService.getDepartment(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Result> editDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        return departmentService.editDepartment(id, departmentDto);
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
