package com.example.rentmate.service;

import com.example.rentmate.dto.LeaseDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;

import java.util.List;

public interface LeaseService {
    Lease createLease(LeaseDTO leaseDTO) throws ResourceNotFoundException;
    Lease updateLease(Long leaseId, LeaseDTO leaseDTO) throws ResourceNotFoundException;
    Lease getLeaseById(Long leaseId) throws ResourceNotFoundException;
    List<Lease> getAllLeases();
    void deleteLease(Long leaseId) throws ResourceNotFoundException;
}
