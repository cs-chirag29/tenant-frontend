package com.example.rentmate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TenantDTO {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 50, message = "First name should be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 50, message = "Last name should be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email should be no more than 100 characters")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;

    @Size(max = 200, message = "Address should be no more than 200 characters")
    private String address;

    @Size(max = 100, message = "City should be no more than 100 characters")
    private String city;

    @Pattern(regexp = "^[0-9]{5,6}$", message = "Zip code should be valid")
    @Size(max = 6, message = "Zip code should be no more than 6 characters")
    private String zipCode;

    @Size(max = 100, message = "State should be no more than 100 characters")
    private String state;

//    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    private String userName;

//    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;

    private String role;
}
