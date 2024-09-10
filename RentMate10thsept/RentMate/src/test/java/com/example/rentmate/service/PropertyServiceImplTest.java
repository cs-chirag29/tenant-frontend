package com.example.rentmate.service;

import com.example.rentmate.dto.PropertiesDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Property;
import com.example.rentmate.repository.PropertyRepository;
import com.example.rentmate.serviceImpl.PropertyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    public PropertyServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProperty() throws ResourceNotFoundException {
        PropertiesDTO dto = new PropertiesDTO();
        dto.setPropertyName("Test Property");
        dto.setCity("Test City");
        dto.setZipCode("12345");
        dto.setAddress("123 Test St");
        dto.setState("Test State");
        dto.setDescription("Test Description");

        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName("Test Property");
        property.setCity("Test City");
        property.setZipCode("12345");
        property.setAddress("123 Test St");
        property.setState("Test State");
        property.setDescription("Test Description");

        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property createdProperty = propertyService.createProperty(dto);

        assertNotNull(createdProperty);
        assertEquals("Test Property", createdProperty.getPropertyName());
    }

    @Test
    public void testGetPropertyById() throws ResourceNotFoundException {
        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName("Test Property");
        property.setCity("Test City");
        property.setZipCode("12345");
        property.setAddress("123 Test St");
        property.setState("Test State");
        property.setDescription("Test Description");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Property foundProperty = propertyService.getPropertyById(1L);

        assertNotNull(foundProperty);
        assertEquals("Test Property", foundProperty.getPropertyName());
    }

    @Test
    public void testGetAllProperties() {
        Property property1 = new Property();
        property1.setPropertyId(1L);
        property1.setPropertyName("Property 1");

        Property property2 = new Property();
        property2.setPropertyId(2L);
        property2.setPropertyName("Property 2");

        when(propertyRepository.findAll()).thenReturn(Arrays.asList(property1, property2));

        List<Property> properties = propertyService.getAllProperties();

        assertNotNull(properties);
        assertEquals(2, properties.size());
    }

    @Test
    public void testDeleteProperty() throws ResourceNotFoundException {
        when(propertyRepository.existsById(1L)).thenReturn(true);

        propertyService.deleteProperty(1L);

        verify(propertyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateProperty() throws ResourceNotFoundException {
        PropertiesDTO dto = new PropertiesDTO();
        dto.setPropertyName("Updated Property");
        dto.setCity("Updated City");
        dto.setZipCode("54321");
        dto.setAddress("456 Updated St");
        dto.setState("Updated State");
        dto.setDescription("Updated Description");

        Property existingProperty = new Property();
        existingProperty.setPropertyId(1L);
        existingProperty.setPropertyName("Old Property");

        Property updatedProperty = new Property();
        updatedProperty.setPropertyId(1L);
        updatedProperty.setPropertyName("Updated Property");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(existingProperty));
        when(propertyRepository.save(any(Property.class))).thenReturn(updatedProperty);

        Property result = propertyService.updateProperty(1L, dto);

        assertNotNull(result);
        assertEquals("Updated Property", result.getPropertyName());
    }
}