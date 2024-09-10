package com.example.rentmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;

    private Long tenantId;
}
