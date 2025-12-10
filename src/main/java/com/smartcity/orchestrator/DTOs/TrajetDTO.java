package com.smartcity.orchestrator.DTOs;

import lombok.*;

//This DTO will not inclued Ligne de transport details
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrajetDTO {
    private Integer id;
    private String codeTrajet;
    private TransportDTO moyenDeTransport;
}