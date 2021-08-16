package com.pccw.ruby.sample.controller;

import com.pccw.ruby.sample.controller.dto.VehicleRequestDTO;
import com.pccw.ruby.sample.controller.dto.VehicleResponseDTO;
import com.pccw.ruby.sample.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class VehicleController {

    @Autowired private VehicleService vehicleService;

    @Operation
    @GetMapping(path = "/v1/vehicles")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<VehicleResponseDTO> vehicleResponseDTOs = vehicleService.findAll();
        return new ResponseEntity<>(vehicleResponseDTOs, HttpStatus.OK);
    }

    @Operation
    @GetMapping(path = "/v1/vehicles/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.findById(id);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.OK);
    }

    @Operation
    @PostMapping(path = "/v1/vehicles")
    public ResponseEntity<VehicleResponseDTO> addVehicle(
            @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.save(null, vehicleRequestDTO);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.CREATED);
    }

    @Operation
    @PutMapping(path = "/v1/vehicles/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable Long id, @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.save(id, vehicleRequestDTO);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.OK);
    }

    @Operation
    @DeleteMapping(path = "/v1/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
