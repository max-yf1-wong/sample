package com.pccw.ruby.sample.proxy;

import com.pccw.ruby.sample.proxy.dto.VehicleProxyRequestDTO;
import com.pccw.ruby.sample.proxy.dto.VehicleProxyResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "vehicle-client", url = "${external.vehicle-provider.url}")
public interface VehicleProxy {

    @GetMapping(path = "/v1/vehicles")
    public ResponseEntity<List<VehicleProxyResponseDTO>> getAllVehicles();

    @GetMapping(path = "/v1/vehicles/{id}")
    public ResponseEntity<VehicleProxyResponseDTO> getVehicleById(@PathVariable("id") Long id);

    @PostMapping(path = "/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleProxyResponseDTO> addVehicle(
            @RequestBody VehicleProxyRequestDTO requestBody);

    @PutMapping(path = "/v1/vehicles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleProxyResponseDTO> updateVehicle(
            @PathVariable("id") Long id, @RequestBody VehicleProxyRequestDTO requestBody);

    @DeleteMapping(path = "/v1/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id);
}
