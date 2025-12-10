package com.smartcity.orchestrator.DTOs;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LigneDeTransportDTO {
    private Integer id;
    private String codeLigne;

    private TerminusDTO terminusDepart;
    private TerminusDTO terminusArrivee;
}
