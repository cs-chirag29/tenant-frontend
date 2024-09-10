package com.example.rentmate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id", nullable = false, updatable = false)
    private Long unitId; // Changed to Long for consistency with other models

    @NotBlank(message = "Unit number is mandatory")
    @Column(name = "unit_number", nullable = false, unique = true)
    private String unitNumber;

    @NotBlank(message = "Unit type is mandatory")
    @Column(name = "unit_type", nullable = false)
    private String unitType;

    @NotBlank(message = "Unit image URL is mandatory")
    @Column(name = "unit_image", nullable = false)
    private String unitImage;

    @NotNull(message = "Rent amount is mandatory")
    @Positive(message = "Rent amount must be a positive number")
    @Column(name = "rent_amount", nullable = false)
    private Double rentAmount;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @NotNull(message = "Property is mandatory")
    @JsonManagedReference
    private Property property;

    @OneToMany(mappedBy = "unit")
    @JsonBackReference
    private Set<Lease> leases = new HashSet<>();


}
