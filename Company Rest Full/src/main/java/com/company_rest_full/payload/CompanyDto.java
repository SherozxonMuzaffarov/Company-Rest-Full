package com.company_rest_full.payload;

import com.company_rest_full.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "Company name can't be empty")
    private String companyName;

    @NotNull(message = "Company director can't be empty")
    private String companyDirector;


    private Long addressId;
}
