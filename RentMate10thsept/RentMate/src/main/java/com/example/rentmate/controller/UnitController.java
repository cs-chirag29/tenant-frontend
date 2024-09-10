package com.example.rentmate.controller;

import com.example.rentmate.dto.UnitDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Unit;
import com.example.rentmate.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping
    public ResponseEntity<Unit> createUnit(@RequestBody @Valid UnitDTO unitDTO) throws ResourceNotFoundException {
        Unit unit =unitService.createUnit(unitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(unit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@PathVariable Long id, @RequestBody @Valid UnitDTO unitDTO) throws ResourceNotFoundException {
        Unit unit =unitService.updateUnit(id, unitDTO);

        return ResponseEntity.ok(unit);
    }

    @GetMapping("/getUnitById/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id) throws ResourceNotFoundException {
        Unit unit = unitService.getUnitById(id);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @GetMapping("/getAllUnits")
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) throws ResourceNotFoundException {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
