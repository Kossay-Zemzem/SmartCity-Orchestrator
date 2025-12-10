package com.smartcity.orchestrator.DTOs;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Train extends TransportDTO {
    private int nbWagons;
}
