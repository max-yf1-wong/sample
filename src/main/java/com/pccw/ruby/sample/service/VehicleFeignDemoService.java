package com.pccw.ruby.sample.service;

import com.pccw.ruby.sample.controller.dto.VehicleRequestDTO;
import com.pccw.ruby.sample.controller.dto.VehicleResponseDTO;
import com.pccw.ruby.sample.proxy.VehicleProxy;
import com.pccw.ruby.sample.proxy.dto.VehicleProxyRequestDTO;
import com.pccw.ruby.sample.proxy.dto.VehicleProxyResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleFeignDemoService {

    @Autowired private VehicleProxy vehicleProxy;

    @Autowired ModelMapper modelMapper;

    public List<VehicleResponseDTO> findAll() {
        // please catch the response if you want special handling for error response
        log.info("ABCDS");
        ResponseEntity<List<VehicleProxyResponseDTO>> vehicles = vehicleProxy.getAllVehicles();

        List<VehicleResponseDTO> vehicleResponseDTOs =
                vehicles.getBody().stream()
                        .map(n -> modelMapper.map(n, VehicleResponseDTO.class))
                        .collect(Collectors.toList());

        return vehicleResponseDTOs;
    }

    public VehicleResponseDTO findById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }
        // please catch the response if you want special handling for error response
        ResponseEntity<VehicleProxyResponseDTO> response = vehicleProxy.getVehicleById(id);

        return modelMapper.map(response.getBody(), VehicleResponseDTO.class);
    }

    public VehicleResponseDTO save(Long id, VehicleRequestDTO vehicleRequestDTO) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }
        VehicleProxyRequestDTO vehicleProxyRequestDTO =
                modelMapper.map(vehicleRequestDTO, VehicleProxyRequestDTO.class);
        ResponseEntity<VehicleProxyResponseDTO> response;
        // please catch the response if you want special handling for error response
        if (id == null) {
            response = vehicleProxy.addVehicle(vehicleProxyRequestDTO);
        } else {
            response = vehicleProxy.updateVehicle(id, vehicleProxyRequestDTO);
        }
        return modelMapper.map(response.getBody(), VehicleResponseDTO.class);
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }

        // please catch the response if you want special handling for error response
        vehicleProxy.deleteVehicle(id);
    }
}
