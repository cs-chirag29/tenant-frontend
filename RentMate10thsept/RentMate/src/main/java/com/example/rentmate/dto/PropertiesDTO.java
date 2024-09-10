package com.example.rentmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertiesDTO {

    @NotBlank(message = "Property name is mandatory")
    @Size(max = 255, message = "Property name should not exceed 255 characters")
    private String propertyName;

    @NotBlank(message = "City is mandatory")
    @Size(max = 100, message = "City name should not exceed 100 characters")
    private String city;

    @NotBlank(message = "Zip code is mandatory")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Zip code should be a 5-digit number or a 9-digit number with a hyphen")
    private String zipCode;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 500, message = "Address should not exceed 500 characters")
    private String address;

    @NotBlank(message = "State is mandatory")
    @Size(max = 100, message = "State name should not exceed 100 characters")
    private String state;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

}
