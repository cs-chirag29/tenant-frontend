package com.example.rentmate.controller;

import com.example.rentmate.dto.TenantDTO;
import com.example.rentmate.dto.UpdateDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.model.UserLogin;
import com.example.rentmate.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;



    @PostMapping("/register")
    public ResponseEntity<String> registerTenant(@Valid @RequestBody TenantDTO tenantDTO) {
            tenantService.registerTenant(tenantDTO);
            return ResponseEntity.ok("Tenant registered successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @Valid @RequestBody UpdateDto updateDto) throws ResourceNotFoundException {
        Tenant tenant = tenantService.updateTenant(id, updateDto);
        return ResponseEntity.ok(tenant);
    }

    @GetMapping("/getTenantById/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) throws ResourceNotFoundException {
        Tenant tenant = tenantService.getTenantById(id);
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }

    @GetMapping("/getAllTenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) throws ResourceNotFoundException {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }
}
