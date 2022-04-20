package com.company_rest_full.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Department name can't be empty")
    private String name;

    @NotNull(message = "Company can't be empty")
    private Long companyId;
}
