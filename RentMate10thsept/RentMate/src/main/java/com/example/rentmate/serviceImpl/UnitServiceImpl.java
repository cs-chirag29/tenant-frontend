package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.UnitDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;
import com.example.rentmate.model.Order;
import com.example.rentmate.model.Property;
import com.example.rentmate.model.Unit;
import com.example.rentmate.repository.LeaseRepository;
import com.example.rentmate.repository.OrderRepository;
import com.example.rentmate.repository.PropertyRepository;
import com.example.rentmate.repository.UnitRepository;
import com.example.rentmate.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Unit createUnit(UnitDTO unitDTO) throws ResourceNotFoundException {
        Unit unit = new Unit();
        unit.setUnitNumber(unitDTO.getUnitNumber());
        unit.setUnitType(unitDTO.getUnitType());
        unit.setUnitImage(unitDTO.getUnitImage());
        unit.setRentAmount(unitDTO.getRentAmount());
        unit.setStatus(unitDTO.getStatus());

        Property property = propertyRepository.findById(unitDTO.getPropertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + unitDTO.getPropertyId()));
        unit.setProperty(property);

        return unitRepository.save(unit);
    }

    @Override
    public Unit updateUnit(Long unitId, UnitDTO unitDTO) throws ResourceNotFoundException {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + unitId));

        unit.setUnitNumber(unitDTO.getUnitNumber());
        unit.setUnitType(unitDTO.getUnitType());
        unit.setUnitImage(unitDTO.getUnitImage());
        unit.setRentAmount(unitDTO.getRentAmount());
        unit.setStatus(unitDTO.getStatus());

        if (unitDTO.getPropertyId() != null) {
            Property property = propertyRepository.findById(unitDTO.getPropertyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + unitDTO.getPropertyId()));
            unit.setProperty(property);
        }

        return unitRepository.save(unit);
    }

    @Override
    public Unit getUnitById(Long unitId) throws ResourceNotFoundException {
        // Retrieve the unit from the repository
        return unitRepository.findById(unitId)
                // If the unit is present, return it
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + unitId));
    }


    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @Override
    public void deleteUnit(Long unitId) throws ResourceNotFoundException {
        // Check if the unit exists before attempting deletion
        if (!unitRepository.existsById(unitId)) {
            throw new ResourceNotFoundException("Unit not found with id: " + unitId);
        }
        unitRepository.deleteById(unitId);
    }
}
