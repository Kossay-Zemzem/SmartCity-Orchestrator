package com.smartcity.orchestrator.controllers;

import com.example.proxy.FactureDTO;
import com.example.proxy.FactureRequest;
import com.smartcity.orchestrator.DTOs.FactureParking;
import com.smartcity.orchestrator.DTOs.ReservationParking;
import com.smartcity.orchestrator.DTOs.TerminusDTO;
import com.smartcity.orchestrator.clients.FactureSOAPClient;
import com.smartcity.orchestrator.clients.ParkingGraphQLClient;
import com.smartcity.orchestrator.clients.TransportRESTClient;
import com.smartcity.orchestrator.utility.SoapFactureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smartcity")
public class OrchController {
    private final TransportRESTClient transportClient ;
    private final ParkingGraphQLClient parkingClient ;
    private final FactureSOAPClient factureClient ;
    private final SoapFactureMapper soapFactureMapper;

    @Autowired
    public OrchController(TransportRESTClient transportClient, ParkingGraphQLClient parkingClient, FactureSOAPClient factureClient, SoapFactureMapper soapFactureMapper) {
        this.transportClient = transportClient;
        this.parkingClient = parkingClient;
        this.factureClient = factureClient;
        this.soapFactureMapper = soapFactureMapper;
    }

    @GetMapping
    public String test() {
        return "Smart City Orchestrator is running.";
    }
    //Scenario N°1 Reservation d'un spot de parking
    @PostMapping("/parking/reservation")
    public FactureParking reserverSpotParking() {
        //0. Creer objet ReservationParking (get it from POST request body later)
        ReservationParking reservation = new ReservationParking();
        reservation.setSpotCode("A14");
        reservation.setEtageLabel("F0");
        reservation.setDurationInHours(2);
        reservation.setRefClient("CLT-2024-001");
        //1.Appeler GraphQL parking pour reserver (ParkingGraphQLClient)
        FactureParking fp= parkingClient.reserveSpot(reservation);
        System.out.println("Facture Parking received in Orchestrator: "+fp.getRefFacture());
        //2. Recevoir et creer objet FactureParking
        FactureRequest factureRequest = soapFactureMapper.mapToFactureRequest(fp);
        factureClient.generateFacture(factureRequest);
        //3. Appeler service SOAP opur genererFacture avec objet "FactureParking" comme "FactureRequest"
        //4. Recevoir la reponse SOAP et retourner FactureParking ou reponse SOAP mappe en FactureParking
        return fp;
    }


    //Parking microservice calls

    //Transport microservice calls
    @GetMapping("/terminus")
    public List<TerminusDTO> getAllTerminus() {
        List<TerminusDTO> terminusList = transportClient.getAllTerminus();
        System.out.println("Terminus List from Transport Service: " + terminusList.get(0).getNom());
        return terminusList ;
    }

    //Facture microservice calls
    @GetMapping("/facture/clients/count")
    public Long countClients() {
        return factureClient.countClients();
    }

    @GetMapping("/facture/clients")
    public Object getAllClients() {
        return factureClient.getAllClients();
    }

    @GetMapping("/facture/{type}")
    public List<FactureDTO> getAllFacturesByType(@PathVariable("type") String type) {
        return factureClient.getAllFactureParType(type);
    }

}
