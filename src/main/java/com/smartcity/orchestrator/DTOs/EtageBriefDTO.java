package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
//This version has less details than EtageDTO
public class EtageBriefDTO {
    private String label;
    private boolean hasPaymentKiosk;
    private List<SpotBriefDTO> spots; // Used for getAllEtages

}
