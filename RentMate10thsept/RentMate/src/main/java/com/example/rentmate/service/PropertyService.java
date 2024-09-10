package com.example.rentmate.service;

import com.example.rentmate.dto.PropertiesDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Property;

import java.util.List;

public interface PropertyService {
    Property createProperty(PropertiesDTO propertyDTO) throws ResourceNotFoundException;
    Property updateProperty(Long propertyId, PropertiesDTO propertyDTO) throws ResourceNotFoundException;
    Property getPropertyById(Long propertyId) throws ResourceNotFoundException;
    List<Property> getAllProperties();
    void deleteProperty(Long propertyId) throws ResourceNotFoundException;
}
