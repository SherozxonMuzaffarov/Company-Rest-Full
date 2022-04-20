package com.company_rest_full.controller;

import com.company_rest_full.entity.Address;
import com.company_rest_full.payload.AddressDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.service.AddressService;
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
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<Result> addAddress(@Valid @RequestBody AddressDto addressDto){
        return addressService.addAddress(addressDto);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getAddress(@PathVariable Long id){
        return  ResponseEntity.ok(addressService.getAddress(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Address>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteAddress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.deleteAddress(id));
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Result> editAddress(@Valid @RequestBody AddressDto addressDto,@PathVariable Long id){
        Result result = addressService.editAddress(addressDto, id);
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
