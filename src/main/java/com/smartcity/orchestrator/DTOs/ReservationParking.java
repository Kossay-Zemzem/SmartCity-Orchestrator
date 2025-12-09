package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationParking {
    private String spotCode;
    private String etageLabel;
    private Integer durationInHours;
    private String refClient;
}
