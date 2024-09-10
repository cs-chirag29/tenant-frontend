package com.example.rentmate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "property_id", nullable = false)
    @NotNull(message = "Property is mandatory")
    private Property property;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "unit_id", nullable = false)
    @NotNull(message = "Unit is mandatory")
    private Unit unit;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "tenant_id", nullable = false)
    @NotNull(message = "Tenant is mandatory")
    private Tenant tenant;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "lease_id")
    private Lease lease;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Payment> payments = new HashSet<>();

    @NotNull(message = "Application date is mandatory")
    @PastOrPresent(message = "Application date cannot be in the future")
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent(message = "Start date cannot be in the past")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    @FutureOrPresent(message = "End date cannot be in the past")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Status is mandatory")
    @Column(name = "status", nullable = false)
    private String status; // Pending, Approved, Rejected

    @Positive(message = "Rent amount must be positive")
    @Column(name = "rent_amount", nullable = false)
    private double rentAmount;

    @Positive(message = "Security deposit must be positive")
    @Column(name = "security_deposit", nullable = false)
    private double securityDeposit;

    @PastOrPresent(message = "Created date cannot be in the future")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @PastOrPresent(message = "Updated date cannot be in the future")
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PastOrPresent(message = "Completed date cannot be in the future")
    @Column(name = "completed_at")
    private LocalDate completedAt;
}
