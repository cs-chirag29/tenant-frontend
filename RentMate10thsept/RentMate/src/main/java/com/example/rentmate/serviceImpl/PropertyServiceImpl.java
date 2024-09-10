package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.PropertiesDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Order;
import com.example.rentmate.model.Property;
import com.example.rentmate.model.Unit;
import com.example.rentmate.repository.OrderRepository;
import com.example.rentmate.repository.PropertyRepository;
import com.example.rentmate.repository.UnitRepository;
import com.example.rentmate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Property createProperty(PropertiesDTO propertyDTO) throws ResourceNotFoundException {
        // Check if the property already exists
//        Property existingProperty = propertyRepository.findByPropertyName(propertyDTO.getPropertyName());
//        if (existingProperty != null) {
//            return existingProperty;
//        }

        Property property = new Property();
        // Set property fields from propertyDTO
        property.setPropertyName(propertyDTO.getPropertyName());
        property.setCity(propertyDTO.getCity());
        property.setZipCode(propertyDTO.getZipCode());
        property.setAddress(propertyDTO.getAddress());
        property.setState(propertyDTO.getState());
        property.setDescription(propertyDTO.getDescription());

        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long propertyId, PropertiesDTO propertyDTO) throws ResourceNotFoundException {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        // Update property fields from propertyDTO
        property.setPropertyName(propertyDTO.getPropertyName());
        property.setCity(propertyDTO.getCity());
        property.setZipCode(propertyDTO.getZipCode());
        property.setAddress(propertyDTO.getAddress());
        property.setState(propertyDTO.getState());

        return propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(Long propertyId) throws ResourceNotFoundException {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public void deleteProperty(Long propertyId) throws ResourceNotFoundException {
        if (!propertyRepository.existsById(propertyId)) {
            throw new ResourceNotFoundException("Property not found with id: " + propertyId);
        }
        propertyRepository.deleteById(propertyId);
    }
}
