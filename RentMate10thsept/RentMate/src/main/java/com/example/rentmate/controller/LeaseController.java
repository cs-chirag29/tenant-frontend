package com.example.rentmate.controller;

import com.example.rentmate.dto.LeaseDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;
import com.example.rentmate.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @PostMapping
    public ResponseEntity<Lease> createLease(@RequestBody @Valid LeaseDTO leaseDTO) throws ResourceNotFoundException {
        Lease lease = leaseService.createLease(leaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(lease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lease> updateLease(@PathVariable Long id, @RequestBody @Valid LeaseDTO leaseDTO) throws ResourceNotFoundException {
        Lease lease = leaseService.updateLease(id, leaseDTO);
        return ResponseEntity.ok(lease);
    }

    @GetMapping("/getLeaseById/{id}")
    public ResponseEntity<Lease> getLeaseById(@PathVariable Long id) throws ResourceNotFoundException {
            Lease lease = leaseService.getLeaseById(id);
        return new ResponseEntity<>(lease, HttpStatus.OK);
    }

    @GetMapping("/getAllLeases")
    public ResponseEntity<List<Lease>> getAllLeases() {
        List<Lease> orders = leaseService.getAllLeases();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLease(@PathVariable Long id) throws ResourceNotFoundException {
        leaseService.deleteLease(id);
        return ResponseEntity.noContent().build();
    }
}
