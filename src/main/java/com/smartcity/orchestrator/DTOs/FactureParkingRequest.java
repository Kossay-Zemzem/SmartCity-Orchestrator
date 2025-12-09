package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactureParkingRequest {
    private String refFacture;
    private String refClient;
    private String codeSpot;
    private String typeSpot; // "VIP" or "NORMAL"
    private boolean isOccupied;
    private String etageLabel;
    private Integer durationInHours;
    private Double prixTTC;

}
