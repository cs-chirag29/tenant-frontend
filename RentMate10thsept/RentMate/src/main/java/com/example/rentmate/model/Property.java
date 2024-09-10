package com.example.rentmate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false, updatable = false)
    private Long propertyId; // Changed to Long for consistency with other models

    @NotBlank(message = "Property name is mandatory")
    @Size(max = 255, message = "Property name should not exceed 255 characters")
    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @NotBlank(message = "City is mandatory")
    @Size(max = 100, message = "City name should not exceed 100 characters")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Zip code is mandatory")
    @Size(min = 5, max = 10, message = "Zip code should be between 5 and 10 characters")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 500, message = "Address should not exceed 500 characters")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "State is mandatory")
    @Size(max = 100, message = "State name should not exceed 100 characters")
    @Column(name = "state", nullable = false)
    private String state;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Unit> units = new HashSet<>();

}
