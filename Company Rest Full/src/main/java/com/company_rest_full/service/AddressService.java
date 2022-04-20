package com.company_rest_full.service;

import com.company_rest_full.entity.Address;
import com.company_rest_full.payload.AddressDto;
import com.company_rest_full.payload.Result;
import com.company_rest_full.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity addAddress(AddressDto addressDto) {

        boolean address1 = addressRepository.existsAddressByHomeNumber(addressDto.getHomeNumber());
        if (address1)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("Address saved before with this home number", false));
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Result("Address saved successfully", true));

    }

    public Object getAddress(Long id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Addres not found with this Id:" + id, false);

        return optionalAddress.get();
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Result deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Addres not found with this Id:" + id, false);

        addressRepository.deleteById(id);
        return  new Result("Address deleted successfully", true);
    }

    public Result editAddress(AddressDto addressDto, Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Addres not found with this Id:" + id, false);

        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(address);
        return new Result("Address saved successfully", true);

    }
}
