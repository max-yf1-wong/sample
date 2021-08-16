package com.pccw.ruby.sample.proxy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleProxyRequestDTO {

    private String name;

    private Short capacity;

    private LocalDateTime productionDate;
}
