package com.company_rest_full.payload;

import com.company_rest_full.entity.Address;
import com.company_rest_full.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyWorkerDto {

    @NotNull(message = "Worker name can't be empty")
    private String name;

    @NotNull(message = "Phone number name can't be empty")
    private String phoneNumber;

    @NotNull(message = "department Id can't be empty")
    private Long departmentID;

    @NotNull(message = "Address Id can't be empty")
    private Long addressId;
}
