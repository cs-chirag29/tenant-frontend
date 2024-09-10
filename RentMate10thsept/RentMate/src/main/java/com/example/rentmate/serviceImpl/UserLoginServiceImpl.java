package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.UserLoginDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.UserLogin;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.repository.UserLoginRepository;
import com.example.rentmate.repository.TenantRepository;
import com.example.rentmate.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserLogin createUserLogin(UserLoginDto userLoginDto) throws ResourceNotFoundException {
        // Check if the username is already registered
        if (isUserAlreadyRegistered(userLoginDto.getUserName())) {
            throw new RuntimeException("Username already taken");
        }

        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userLoginDto.getUserName());
        userLogin.setPassword(passwordEncoder.encode(userLoginDto.getPassword())); // Encode password

        // Set tenant if tenantId is provided
        if (userLoginDto.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(userLoginDto.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + userLoginDto.getTenantId()));
            userLogin.setTenant(tenant);
        }

        return userLoginRepository.save(userLogin);
    }

    @Override
    public Optional<UserLogin> authenticateUser(String username) {
        // Validate input
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }
        return userLoginRepository.findByUserName(username);
    }

    @Override
    public boolean isUserAlreadyRegistered(String userName) {
        // Validate input
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }
        return userLoginRepository.findByUserName(userName).isPresent();
    }

    @Override
    public UserLogin updateUserLogin(long id, UserLoginDto userLoginDto) throws ResourceNotFoundException {
        UserLogin userLogin = userLoginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserLogin not found with id: " + id));

        // Update fields
        userLogin.setUserName(userLoginDto.getUserName());
        userLogin.setPassword(passwordEncoder.encode(userLoginDto.getPassword())); // Encode password

        // Update tenant if tenantId is provided
        if (userLoginDto.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(userLoginDto.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + userLoginDto.getTenantId()));
            userLogin.setTenant(tenant);
        }

        return userLoginRepository.save(userLogin);
    }

    @Override
    public UserLogin getUserLoginById(long id) throws ResourceNotFoundException {
        return userLoginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserLogin not found with id: " + id));
    }

    @Override
    public List<UserLogin> getAllUserLogins() {
        return userLoginRepository.findAll();
    }

    @Override
    public void deleteUserLogin(long id) throws ResourceNotFoundException {
        // Check if the UserLogin exists before deleting
        if (!userLoginRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserLogin not found with id: " + id);
        }
        userLoginRepository.deleteById(id);
    }
}
