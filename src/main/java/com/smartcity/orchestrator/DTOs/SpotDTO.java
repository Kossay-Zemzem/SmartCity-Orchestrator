package com.smartcity.orchestrator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SpotDTO {
    private String id;
    private String code;
    private String type;
    private boolean isOccupied;

}
