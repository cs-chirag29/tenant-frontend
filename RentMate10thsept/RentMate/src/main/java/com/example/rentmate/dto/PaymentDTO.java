package com.example.rentmate.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PaymentDTO {

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Payment date is mandatory")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    @NotNull(message = "Lease ID is mandatory")
    private Long leaseId;

    @NotNull(message = "Tenant ID is mandatory")
    private Long tenantId;

    @NotNull(message = "Order ID is mandatory")
    private Long orderId;
}
