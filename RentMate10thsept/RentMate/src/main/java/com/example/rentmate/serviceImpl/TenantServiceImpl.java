package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.TenantDTO;
import com.example.rentmate.dto.UpdateDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.exception.TenantAlreadyExistsException;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.model.UserLogin;
import com.example.rentmate.repository.TenantRepository;
import com.example.rentmate.repository.UserLoginRepository;
import com.example.rentmate.service.TenantService;
import com.example.rentmate.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserLoginService userLoginService;


    @Override
    public void registerTenant(TenantDTO tenantDTO) {
        // Check if a tenant with the same email already exists
        Optional<Tenant> existingTenantByEmail = tenantRepository.findByEmail(tenantDTO.getEmail());

        // Check if a user login with the same username already exists
        Optional<UserLogin> existingUserLoginByUsername = userLoginRepository.findByUserName(tenantDTO.getUserName());

        if (existingTenantByEmail.isPresent() || existingUserLoginByUsername.isPresent()) {
            throw new TenantAlreadyExistsException("Tenant with this email or username already exists.");
        }

        // Create and save the Tenant entity
        Tenant tenant = new Tenant();
        tenant.setFirstName(tenantDTO.getFirstName());
        tenant.setLastName(tenantDTO.getLastName());
        tenant.setEmail(tenantDTO.getEmail());
        tenant.setPhoneNumber(tenantDTO.getPhoneNumber());
        tenant.setAddress(tenantDTO.getAddress());
        tenant.setCity(tenantDTO.getCity());
        tenant.setZipCode(tenantDTO.getZipCode());
        tenant.setState(tenantDTO.getState());

        // Set role based on existing tenants count
        tenant.setRole(tenantRepository.count() == 0 ? "super-admin" : "user");

        // Create and save the UserLogin entity
        UserLogin userLogin= new UserLogin();
        userLogin.setUserName(tenantDTO.getUserName());
        userLogin.setPassword(passwordEncoder.encode(tenantDTO.getPassword())); // Encode passwordzyyyy

        Tenant temp= tenantRepository.save(tenant);
        userLogin.setTenant(tenant);
        userLoginRepository.save(userLogin);
        tenant.setUserLogin(userLogin);

        tenantRepository.save(temp);

    }

    @Override
    public Tenant updateTenant(Long tenantId, UpdateDto updateDto) throws ResourceNotFoundException {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + tenantId));


        // Check if the email is already in use by another tenant
        Optional<Tenant> existingTenantByEmail = tenantRepository.findByEmail(updateDto.getEmail());
        if (existingTenantByEmail.isPresent() && existingTenantByEmail.get().getTenantId() != tenantId) {
            throw new TenantAlreadyExistsException("Email is already in use by another tenant.");
        }


        // Update tenant fields from tenantDTO
        tenant.setFirstName(updateDto.getFirstName());
        tenant.setLastName(updateDto.getLastName());
        tenant.setEmail(updateDto.getEmail());
        tenant.setPhoneNumber(updateDto.getPhoneNumber());
        tenant.setAddress(updateDto.getAddress());
        tenant.setCity(updateDto.getCity());
        tenant.setZipCode(updateDto.getZipCode());
        tenant.setState(updateDto.getState());
        tenant.setRole(updateDto.getRole());

        // Save updated tenant to the repository
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant getTenantById(Long tenantId) throws ResourceNotFoundException {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + tenantId));
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public void deleteTenant(Long tenantId) throws ResourceNotFoundException {
        if (!tenantRepository.existsById(tenantId)) {
            throw new ResourceNotFoundException("Tenant not found with id: " + tenantId);
        }
        tenantRepository.deleteById(tenantId);
    }
}
