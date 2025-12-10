package com.smartcity.orchestrator.DTOs;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EtageDTO {
    private String id;
    private String label;
    private int capacity;
    private boolean hasPaymentKiosk;
    private List<SpotDTO> spots; // Used for getAllEtages

}
