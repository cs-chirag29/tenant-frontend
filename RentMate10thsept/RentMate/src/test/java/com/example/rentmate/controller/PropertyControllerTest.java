package com.example.rentmate.controller;

import com.example.rentmate.dto.PropertiesDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Property;
import com.example.rentmate.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PropertyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();
    }

    @Test
    public void testCreateProperty() throws Exception {
        PropertiesDTO propertiesDTO = new PropertiesDTO();
        propertiesDTO.setPropertyName("Test Property");
        propertiesDTO.setCity("Test City");
        propertiesDTO.setZipCode("12345");
        propertiesDTO.setAddress("123 Test St");
        propertiesDTO.setState("Test State");
        propertiesDTO.setDescription("Test Description");

        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName(propertiesDTO.getPropertyName());
        property.setCity(propertiesDTO.getCity());
        property.setZipCode(propertiesDTO.getZipCode());
        property.setAddress(propertiesDTO.getAddress());
        property.setState(propertiesDTO.getState());
        property.setDescription(propertiesDTO.getDescription());

        when(propertyService.createProperty(any(PropertiesDTO.class))).thenReturn(property);

        mockMvc.perform(post("/properties")
                        .contentType("application/json")
                        .content("{\"propertyName\":\"Test Property\",\"city\":\"Test City\",\"zipCode\":\"12345\",\"address\":\"123 Test St\",\"state\":\"Test State\",\"description\":\"Test Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.propertyId").value(1L))
                .andExpect(jsonPath("$.propertyName").value("Test Property"));
    }

    @Test
    public void testUpdateProperty() throws Exception {
        PropertiesDTO propertiesDTO = new PropertiesDTO();
        propertiesDTO.setPropertyName("Updated Property");
        propertiesDTO.setCity("Updated City");
        propertiesDTO.setZipCode("54321");
        propertiesDTO.setAddress("456 Updated St");
        propertiesDTO.setState("Updated State");
        propertiesDTO.setDescription("Updated Description");

        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName(propertiesDTO.getPropertyName());
        property.setCity(propertiesDTO.getCity());
        property.setZipCode(propertiesDTO.getZipCode());
        property.setAddress(propertiesDTO.getAddress());
        property.setState(propertiesDTO.getState());
        property.setDescription(propertiesDTO.getDescription());

        when(propertyService.updateProperty(anyLong(), any(PropertiesDTO.class))).thenReturn(property);

        mockMvc.perform(put("/properties/1")
                        .contentType("application/json")
                        .content("{\"propertyName\":\"Updated Property\",\"city\":\"Updated City\",\"zipCode\":\"54321\",\"address\":\"456 Updated St\",\"state\":\"Updated State\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyId").value(1L))
                .andExpect(jsonPath("$.propertyName").value("Updated Property"));
    }

    @Test
    public void testGetPropertyById() throws Exception {
        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName("Test Property");
        property.setCity("Test City");
        property.setZipCode("12345");
        property.setAddress("123 Test St");
        property.setState("Test State");
        property.setDescription("Test Description");

        when(propertyService.getPropertyById(anyLong())).thenReturn(property);

        mockMvc.perform(get("/properties/getPropertyById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyId").value(1L))
                .andExpect(jsonPath("$.propertyName").value("Test Property"));
    }

    @Test
    public void testGetAllProperties() throws Exception {
        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyName("Test Property");

        when(propertyService.getAllProperties()).thenReturn(Collections.singletonList(property));

        mockMvc.perform(get("/properties/getAllProperties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].propertyId").value(1L))
                .andExpect(jsonPath("$[0].propertyName").value("Test Property"));
    }

    @Test
    public void testDeleteProperty() throws Exception {
        doNothing().when(propertyService).deleteProperty(anyLong());

        mockMvc.perform(delete("/properties/1"))
                .andExpect(status().isNoContent());
    }




}
