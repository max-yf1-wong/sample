package com.pccw.ruby.sample.service;

import com.pccw.ruby.common.exception.DataNotFoundException;
import com.pccw.ruby.sample.controller.dto.VehicleRequestDTO;
import com.pccw.ruby.sample.controller.dto.VehicleResponseDTO;
import com.pccw.ruby.sample.entity.Vehicle;
import com.pccw.ruby.sample.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleService {

    @Autowired private VehicleRepository vehicleRepository;

    @Autowired ModelMapper modelMapper;

    public List<VehicleResponseDTO> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        List<VehicleResponseDTO> vehicleResponseDTOs =
                vehicles.stream()
                        .map(n -> modelMapper.map(n, VehicleResponseDTO.class))
                        .collect(Collectors.toList());

        return vehicleResponseDTOs;
    }

    public VehicleResponseDTO findById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            throw new DataNotFoundException(String.format("Vehicle with id %d is not found", id));
        }

        return modelMapper.map(vehicle.get(), VehicleResponseDTO.class);
    }

    @Transactional
    public VehicleResponseDTO save(Long id, VehicleRequestDTO vehicleRequestDTO) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }
        Vehicle vehicle = modelMapper.map(vehicleRequestDTO, Vehicle.class);
        vehicle.setId(id);
        vehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleResponseDTO.class);
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    String.format("id must be a positive number, id: %d", id));
        }
        try {
            vehicleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Vehicle with id {} is not found", id);
            throw new DataNotFoundException(String.format("Vehicle with id %d is not found", id));
        }
    }
}
