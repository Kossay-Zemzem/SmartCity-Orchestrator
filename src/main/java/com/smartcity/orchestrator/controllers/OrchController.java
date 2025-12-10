package com.smartcity.orchestrator.controllers;

import com.example.proxy.FactureDTO;
import com.example.proxy.FactureRequest;
import com.smartcity.orchestrator.DTOs.FactureParking;
import com.smartcity.orchestrator.DTOs.LigneDeTransportDTO;
import com.smartcity.orchestrator.DTOs.ReservationParking;
import com.smartcity.orchestrator.DTOs.TerminusDTO;
import com.smartcity.orchestrator.clients.FactureSOAPClient;
import com.smartcity.orchestrator.clients.ParkingGraphQLClient;
import com.smartcity.orchestrator.clients.TransportRESTClient;
import com.smartcity.orchestrator.utility.SoapFactureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smartcity")
public class OrchController {
    private static final Logger LG = LogManager.getLogger();

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

    //Mixed microservice calls --------------------------------------

    //Scenario de Reservation d'un spot de parking par l'appelle POST /parking/reservation
    @PostMapping("/parking/reservation")
    public FactureParking reserverSpotParking(@RequestBody ReservationParking reservation) {

        //1.Appeler GraphQL parking pour reserver (ParkingGraphQLClient)
        FactureParking facture= parkingClient.reserveSpot(reservation);
        LG.info("Facture Parking received in Orchestrator: {}", facture.getRefFacture());

        //2. Recevoir objet FactureParking et le transformer en FactureRequest pour appel SOAP
        FactureRequest factureRequest = soapFactureMapper.mapToFactureRequest(facture);

        //3. Appeler service SOAP pour enregistrer la facture
        factureClient.generateFacture(factureRequest);

        //4. Renvoyer la facture
        return facture;
    }

    //Parking microservice calls --------------------------------------

    //Transport microservice calls --------------------------------------
    @GetMapping("/transport/terminus")
    public List<TerminusDTO> getAllTerminus() {
        List<TerminusDTO> terminusList = transportClient.getAllTerminus();
        LG.info("Terminus List from Transport Service: {}", terminusList.get(0).getNom());
        return terminusList ;
    }

    @GetMapping("/transport/lignes")
    public List<LigneDeTransportDTO> getAllLignesDeTransport() {
        return transportClient.getAllLignesDeTransport();
    }




    //Facture microservice calls --------------------------------------
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
