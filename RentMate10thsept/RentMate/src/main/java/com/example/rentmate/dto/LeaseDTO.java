package com.example.rentmate.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaseDTO {

    @NotNull(message = "Start date is mandatory")
//    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
//    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;

    @NotNull(message = "Security deposit is mandatory")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Security deposit must be a positive number with up to 2 decimal places")
    private String securityDeposit;

    @NotNull(message = "Tenant Id is mandatory")
    private Long tenantId; // To link to an existing tenant
    @NotNull(message = "Unit id is mandatory")
    private Long unitId; // To link to an existing unit

}
