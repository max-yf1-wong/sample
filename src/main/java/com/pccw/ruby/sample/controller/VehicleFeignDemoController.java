package com.pccw.ruby.sample.controller;

import com.pccw.ruby.sample.controller.dto.VehicleRequestDTO;
import com.pccw.ruby.sample.controller.dto.VehicleResponseDTO;
import com.pccw.ruby.sample.service.VehicleFeignDemoService;
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
public class VehicleFeignDemoController {

    @Autowired private VehicleFeignDemoService vehicleFeignDemoService;

    @Operation
    @GetMapping(path = "/v1/vehicles-feign-demo")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<VehicleResponseDTO> vehicleResponseDTOs = vehicleFeignDemoService.findAll();
        return new ResponseEntity<>(vehicleResponseDTOs, HttpStatus.OK);
    }

    @Operation
    @GetMapping(path = "/v1/vehicles-feign-demo/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        VehicleResponseDTO vehicleResponseDTO = vehicleFeignDemoService.findById(id);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.OK);
    }

    @Operation
    @PostMapping(path = "/v1/vehicles-feign-demo")
    public ResponseEntity<VehicleResponseDTO> addVehicle(
            @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO =
                vehicleFeignDemoService.save(null, vehicleRequestDTO);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.CREATED);
    }

    @Operation
    @PutMapping(path = "/v1/vehicles-feign-demo/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable Long id, @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleFeignDemoService.save(id, vehicleRequestDTO);
        return new ResponseEntity<>(vehicleResponseDTO, HttpStatus.OK);
    }

    @Operation
    @DeleteMapping(path = "/v1/vehicles-feign-demo/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleFeignDemoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
