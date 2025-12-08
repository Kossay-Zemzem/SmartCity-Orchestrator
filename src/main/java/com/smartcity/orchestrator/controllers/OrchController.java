package com.smartcity.orchestrator.controllers;

import com.smartcity.orchestrator.DTOs.TerminusDTO;
import com.smartcity.orchestrator.clients.TransportRESTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/smartcity")
public class OrchController {
    private final TransportRESTClient transportClient ;

    @Autowired
    public OrchController(TransportRESTClient transportClient) {
        this.transportClient = transportClient;
    }

    @GetMapping
    public String test() {
        return "Smart City Orchestrator is running.";
    }

    //Transport service calls
    @GetMapping("/terminus")
    public List<TerminusDTO> getAllTerminus() {
         List<TerminusDTO> terminusList = transportClient.getAllTerminus();
         System.out.println("Terminus List from Transport Service: " + terminusList.get(0).getNom());
         return terminusList ;
    }
}
