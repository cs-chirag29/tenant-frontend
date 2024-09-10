package com.example.rentmate.service;

import com.example.rentmate.dto.UnitDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Unit;

import java.util.List;

public interface UnitService {
    Unit createUnit(UnitDTO unitDTO) throws ResourceNotFoundException;
    Unit updateUnit(Long unitId, UnitDTO unitDTO) throws ResourceNotFoundException;
    Unit getUnitById(Long unitId) throws ResourceNotFoundException;
    List<Unit> getAllUnits();
    void deleteUnit(Long unitId) throws ResourceNotFoundException;
}
