package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactureParking {
    private String refFacture;
    private String refClient;
    private String etageLabel;
    private Integer durationInHours;
    private Double prixTTC;
    private SpotDTO spot;
}
