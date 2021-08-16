package com.pccw.ruby.sample.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleResponseDTO {
    private Long id;

    private String name;

    private Short capacity;

    private LocalDateTime productionDate;
}
