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
//    private final static String BASE_URL = "http://localhost:8081"; //calls the service directly (hardcoded port)
    private final static String BASE_URL = "http://localhost:9000/transport"; //calls microservice via API Gateway (service names "transport")

    public TransportRESTClient(){
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }


    public List<TerminusDTO> getAllTerminus() {
        //call GET /terminus of the Transport microservice
        return restClient.get()
                .uri("/terminus")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TerminusDTO>>() {});
    }
}
