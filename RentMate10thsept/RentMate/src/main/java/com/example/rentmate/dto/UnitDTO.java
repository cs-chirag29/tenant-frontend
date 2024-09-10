package com.example.rentmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UnitDTO {

    @NotBlank(message = "Unit number is mandatory")
    @Pattern(regexp = "\\d+", message = "Unit number must be numeric")
    private String unitNumber;

    @NotBlank(message = "Unit type is mandatory")
    private String unitType;

    @NotBlank(message = "Unit image URL is mandatory")
    private String unitImage;

    @NotNull(message = "Rent amount is mandatory")
    @Positive(message = "Rent amount must be a positive number")
    private Double rentAmount;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "^(Available|Not Available)$", message = "Status must be one of 'Available' or 'Not Available'")
    private String status;

    @NotNull(message = "Property ID is mandatory")
    @Positive(message = "Property ID must be a positive number")
    private Long propertyId;
}
