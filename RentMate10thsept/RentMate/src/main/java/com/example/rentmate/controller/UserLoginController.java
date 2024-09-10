package com.example.rentmate.controller;

import com.example.rentmate.dto.UserLoginDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.model.UserLogin;
import com.example.rentmate.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-logins")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Login
    @PostMapping
    public ResponseEntity<Tenant> loginUser(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        Tenant tenant=null;
        //Authentication
        Optional<UserLogin> userLogin = userLoginService.authenticateUser(username);
        if (userLogin.isPresent()) {
            if (passwordEncoder.matches(password, userLogin.get().getPassword())) {
                tenant = userLogin.get().getTenant();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(tenant);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserLogin> updateUserLogin(@PathVariable long id, @RequestBody UserLoginDto userLoginDto) throws ResourceNotFoundException {
        UserLogin userLogin = userLoginService.updateUserLogin(id, userLoginDto);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @GetMapping("/getUserLoginById/{id}")
    public ResponseEntity<UserLogin> getUserLoginById(@PathVariable long id) throws ResourceNotFoundException {
        UserLogin userLogin = userLoginService.getUserLoginById(id);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @GetMapping("/getAllUserLogins")
    public ResponseEntity<List<UserLogin>> getAllUserLogins() {
        List<UserLogin> userLogins = userLoginService.getAllUserLogins();
        return ResponseEntity.ok(userLogins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLogin(@PathVariable long id) throws ResourceNotFoundException {
        userLoginService.deleteUserLogin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

