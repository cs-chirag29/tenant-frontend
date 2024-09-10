package com.example.rentmate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "leases")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lease_id", nullable = false, updatable = false)
    private int leaseId;

    @NotNull(message = "Start date is mandatory")
//    @PastOrPresent(message = "Start date cannot be in the future")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
//    @PastOrPresent(message = "End date cannot be in the future")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Security deposit is mandatory")
    @Positive(message = "Security deposit must be a positive number")
    @Column(name = "security_deposit", nullable = false)
    private Double securityDeposit;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    @NotNull(message = "Tenant is mandatory")
    @JsonManagedReference
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    @NotNull(message = "Unit is mandatory")
    @JsonManagedReference
    private Unit unit;

//    @OneToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
//    @JsonBackReference
//    private Order order;

}
