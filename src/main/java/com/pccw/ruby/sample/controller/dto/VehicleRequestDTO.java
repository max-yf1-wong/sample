package com.pccw.ruby.sample.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class VehicleRequestDTO {
    @NotBlank
    @Size(max = 50, message = "length must be less than or equal to 50")
    private String name;

    @NotNull
    @Min(value = 2, message = "must be between 2 and 7")
    @Max(value = 7, message = "must be between 2 and 7")
    private Short capacity;

    @Past private LocalDateTime productionDate;
}
