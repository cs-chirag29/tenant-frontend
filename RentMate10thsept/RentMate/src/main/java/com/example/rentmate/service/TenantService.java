package com.example.rentmate.service;

import com.example.rentmate.dto.TenantDTO;
import com.example.rentmate.dto.UpdateDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Tenant;

import java.util.List;

public interface TenantService {

    void registerTenant(TenantDTO tenantDTO);
    Tenant updateTenant(Long tenantId, UpdateDto updateDto) throws ResourceNotFoundException;
    Tenant getTenantById(Long tenantId) throws ResourceNotFoundException;
    List<Tenant> getAllTenants();
    void deleteTenant(Long tenantId) throws ResourceNotFoundException;
}
