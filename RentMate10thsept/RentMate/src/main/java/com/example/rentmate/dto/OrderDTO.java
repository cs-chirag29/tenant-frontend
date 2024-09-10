package com.example.rentmate.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderDTO {

    private Long id; // Optional: If you want to include the ID for updates

    @NotNull(message = "Property ID is mandatory")
    private Long propertyId;

    @NotNull(message = "Unit ID is mandatory")
    private Long unitId;

    @NotNull(message = "Tenant ID is mandatory")
    private Long tenantId;

    @NotNull(message = "Application date is mandatory")
    @PastOrPresent(message = "Application date cannot be in the future")
    private LocalDate applicationDate;

    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    @FutureOrPresent(message = "End date cannot be in the past")
    private LocalDate endDate;

    @NotNull(message = "Status is mandatory")
    @Pattern(regexp = "^(Pending|Approved|Rejected)$", message = "Status must be either Pending, Approved, or Rejected")
    private String status;

    @NotNull(message = "Rent amount is mandatory")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Rent amount must be a positive number with up to 2 decimal places")
    private String rentAmount;

    @NotNull(message = "Security deposit is mandatory")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Security deposit must be a positive number with up to 2 decimal places")
    private String securityDeposit;

    @PastOrPresent(message = "Created date cannot be in the future")
    private LocalDate createdAt;

    @PastOrPresent(message = "Updated date cannot be in the future")
    private LocalDate updatedAt;

    @PastOrPresent(message = "Completed date cannot be in the future")
    private LocalDate completedAt;
}
