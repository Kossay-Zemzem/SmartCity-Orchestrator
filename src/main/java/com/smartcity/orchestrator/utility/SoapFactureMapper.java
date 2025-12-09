package com.smartcity.orchestrator.utility;

import com.example.proxy.FactureRequest;
import com.smartcity.orchestrator.DTOs.FactureParking;
import org.springframework.stereotype.Component;

//Helper class to map FactureParking to generated class FactureRequest
@Component
public class SoapFactureMapper {
    public FactureRequest mapToFactureRequest(FactureParking fp) {
        FactureRequest request = new FactureRequest();
        request.setRefFacture(fp.getRefFacture());
        request.setRefClient(fp.getRefClient());
        request.setCodeSpot(fp.getSpot().getCode());
        request.setTypeSpot(fp.getSpot().getType());
        request.setIsOccupied(true); //Spot will be occupied after reservation
        request.setEtageLabel(fp.getEtageLabel());
        request.setDurationInHours(fp.getDurationInHours());
        request.setPrixTTC(fp.getPrixTTC());
        return request;
    }
}
