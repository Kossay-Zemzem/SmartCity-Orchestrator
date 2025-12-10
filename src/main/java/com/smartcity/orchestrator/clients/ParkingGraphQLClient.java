package com.smartcity.orchestrator.clients;

import com.smartcity.orchestrator.DTOs.FactureParking;
import com.smartcity.orchestrator.DTOs.ReservationParking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ParkingGraphQLClient {
    private static final Logger LG = LogManager.getLogger();
    private final HttpSyncGraphQlClient graphQlClient;
    private final static String BASE_URL = "http://localhost:9000/parking/graphql"; //calls statically to graphql endpoint of parking microservice

    public ParkingGraphQLClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
        this.graphQlClient = HttpSyncGraphQlClient.builder(restClient)
                .build();
    }

    public FactureParking reserveSpot(ReservationParking reservation) {
        String mutation = """
            mutation ReserveSpot($input: Reservation!) {
                reserveSpot(Reservation: $input) {
                    refFacture
                    refClient
                    etageLabel
                    durationInHours
                    prixTTC
                    spot {
                        id
                        code
                        type
                        isOccupied
                    }
                }
            }
        """;

        // 2. Execute the mutation using the HttpSyncGraphQlClient
        FactureParking result = graphQlClient.document(mutation)
                // 3. Bind the 'Reservation' input object to the 'input' variable
                .variable("input", reservation)
                // 4. Specify the path to the desired data (the mutation name)
                .retrieve("reserveSpot")
                // 5. Specify the target type for deserialization
                .toEntity(FactureParking.class)
                // 6. Execute synchronously and get the result (since it's HttpSyncGraphQlClient)
                .block();
        LG.info("Received FactureParking from Parking Service: {}", result);

        return result;
    }
}
