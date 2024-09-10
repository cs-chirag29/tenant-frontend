package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.LeaseDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;
import com.example.rentmate.model.Order;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.model.Unit;
import com.example.rentmate.repository.LeaseRepository;
import com.example.rentmate.repository.OrderRepository;
import com.example.rentmate.repository.TenantRepository;
import com.example.rentmate.repository.UnitRepository;
import com.example.rentmate.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Lease createLease(LeaseDTO leaseDTO) throws ResourceNotFoundException {
        Lease lease = new Lease();
        lease.setStartDate(leaseDTO.getStartDate());
        lease.setEndDate(leaseDTO.getEndDate());
        lease.setSecurityDeposit(Double.valueOf(leaseDTO.getSecurityDeposit()));

        Tenant tenant = tenantRepository.findById(leaseDTO.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + leaseDTO.getTenantId()));
        lease.setTenant(tenant);

        Unit unit = unitRepository.findById(leaseDTO.getUnitId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + leaseDTO.getUnitId()));
        lease.setUnit(unit);



        return leaseRepository.save(lease);
    }

    @Override
    public Lease updateLease(Long leaseId, LeaseDTO leaseDTO) throws ResourceNotFoundException {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found with id: " + leaseId));

        lease.setStartDate(leaseDTO.getStartDate());
        lease.setEndDate(leaseDTO.getEndDate());
        lease.setSecurityDeposit(Double.valueOf(leaseDTO.getSecurityDeposit()));

        if (leaseDTO.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(leaseDTO.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + leaseDTO.getTenantId()));
            lease.setTenant(tenant);
        }

        if (leaseDTO.getUnitId() != null) {
            Unit unit = unitRepository.findById(leaseDTO.getUnitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + leaseDTO.getUnitId()));
            lease.setUnit(unit);
        }

        return leaseRepository.save(lease);
    }

    @Override
    public Lease getLeaseById(Long leaseId) throws ResourceNotFoundException {
        return leaseRepository.findById(leaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found with id: " + leaseId));
    }

    @Override
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @Override
    public void deleteLease(Long leaseId) throws ResourceNotFoundException {
        if (!leaseRepository.existsById(leaseId)) {
            throw new ResourceNotFoundException("Lease not found with id: " + leaseId);
        }
        leaseRepository.deleteById(leaseId);
    }
}
