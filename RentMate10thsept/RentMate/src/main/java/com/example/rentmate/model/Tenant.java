package com.example.rentmate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_id", nullable = false, updatable = false)
    private long tenantId;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Pattern(regexp = "^[0-9]{5,6}$", message = "Zip code should be valid")
    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "state")
    private String state;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Lease> leases = new HashSet<>();

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Payment> payments = new HashSet<>();

    @OneToOne(mappedBy = "tenant")
    @JsonBackReference
    private UserLogin userLogin;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Order> orders = new HashSet<>();
}
