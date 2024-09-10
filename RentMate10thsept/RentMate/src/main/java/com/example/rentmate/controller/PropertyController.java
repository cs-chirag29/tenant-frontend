package com.example.rentmate.controller;

import com.example.rentmate.dto.PropertiesDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Property;
import com.example.rentmate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody @Valid PropertiesDTO propertiesDTO) throws ResourceNotFoundException {
        Property property = propertyService.createProperty(propertiesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody PropertiesDTO propertiesDTO) throws ResourceNotFoundException {
        Property property = propertyService.updateProperty(id, propertiesDTO);

        return ResponseEntity.ok(property);
    }

    @GetMapping("/getPropertyById/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) throws ResourceNotFoundException {
        Property property = propertyService.getPropertyById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @GetMapping("/getAllProperties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) throws ResourceNotFoundException {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
