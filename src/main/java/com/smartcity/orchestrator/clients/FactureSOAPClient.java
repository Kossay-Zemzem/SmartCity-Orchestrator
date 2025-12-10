package com.smartcity.orchestrator.clients;

import com.example.proxy.*; // Generated classes from WSDL
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureSOAPClient {
    ServiceFactureAPI_Service service = new ServiceFactureAPI_Service();
    ServiceFactureAPI stub = service.getServiceFactureAPIPort(); //obtenir le stub pour faire des appels SOAP

    public FactureParking generateFacture(FactureRequest request) {
        return stub.generateFacture(request);
    }

    public Long countClients(){
        return stub.getClientCount();
    }

    public List<Client> getAllClients() {
        return stub.getAllClients();
    }

    public List<FactureDTO> getAllFactureParType(String type) {
        return stub.getAllFactureParType(TypeFacture.fromValue(type));
    }
}
