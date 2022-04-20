package com.company_rest_full.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {


    @NotNull(message = "Street can't be empty")
    private String street;

    @NotNull(message = "Home Number can't be empty")
    private String homeNumber;
}
