package com.smartcity.orchestrator.clients;

import com.smartcity.orchestrator.DTOs.TerminusDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TransportRESTClient {

    private final RestClient restClient;
//    private final static String BASE_URL = "http://localhost:8081/transport"; //calls the service directly
    private final static String BASE_URL = "http://localhost:9000/transport"; //calls microservice names transport via gateway on port 9000

    public TransportRESTClient(){
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }


    public List<TerminusDTO> getAllTerminus() {
        //call GET http://localhost:8081/transport/terminus
        return restClient.get()
                .uri("/terminus")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TerminusDTO>>() {});
    }
}
