package com.smartcity.orchestrator.clients;

import com.example.proxy.*;
//import com.smartcity.proxy.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class FactureSOAPClient {

    @Value("${parking.soap.url:http://localhost:8082/services/API}")
    private String soapEndpointUrl;

//    ServiceFactureAPI stub = new ServiceFactureAPI_Service().getServiceFactureAPIPort();

    ServiceFactureAPI_Service service = new ServiceFactureAPI_Service();
    ServiceFactureAPI stub = service.getServiceFactureAPIPort();

//    public FactureParking generateFacture(FactureRequest request) {
//        return stub.generateFacture(request);
//    }

    public Long countClients(){
        return stub.getClientCount();
    }
}
