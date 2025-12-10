package com.smartcity.orchestrator.DTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

//JACKSON Compares fields and creates the subclass that matches best
//Example: if "numWagons" is present, it will create a Train instance
//For this to work reliably, each subclass should have at least one unique field (in our case "numSerie" for Bus and "nbWagons" for Train)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bus.class),
        @JsonSubTypes.Type(value = Train.class)
})

public abstract class TransportDTO {

    private Integer id;
    private String marque;
    private String nom;
    private int capacite; // nombre de places
    private StatutTransport statut;

//    private Set<Trajet> trajets = new HashSet<>();
}
