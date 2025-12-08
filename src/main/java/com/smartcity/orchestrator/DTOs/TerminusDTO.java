package com.smartcity.orchestrator.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TerminusDTO {

    private Integer id;
    private String nom;
    private String adresse;

    private Set<LigneDeTransportDTO> lignesDepart;
    private Set<LigneDeTransportDTO> lignesArrivee;
}

