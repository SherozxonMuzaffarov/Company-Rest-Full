package com.company_rest_full.repository;

import com.company_rest_full.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsAddressByHomeNumber (String homeNumber);
}
