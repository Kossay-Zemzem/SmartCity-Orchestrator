package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//This version has less details than SpotDTO
public class SpotBriefDTO {
    private String code;
    private String type;
    private boolean isOccupied;
}
