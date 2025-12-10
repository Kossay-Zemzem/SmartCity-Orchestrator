package com.smartcity.orchestrator.clients;

import com.smartcity.orchestrator.DTOs.EtageBriefDTO;
import com.smartcity.orchestrator.DTOs.EtageDTO;
import com.smartcity.orchestrator.DTOs.FactureParking;
import com.smartcity.orchestrator.DTOs.ReservationParking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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

    public List<EtageDTO> getEmptySpots() {
        String query = """
                    query EmptySpots {
                        emptySpots {
                            id
                            label
                            capacity
                            hasPaymentKiosk
                            spots {
                                id
                                code
                                type
                                isOccupied
                            }
                        }
                    }
                """;

        // The result is a List<Etage>, so we use .toEntityList(Etage.class)
        List<EtageDTO> result = graphQlClient.document(query)
                .retrieve("emptySpots")
                .toEntityList(EtageDTO.class)
                .block();
        return result;
    }

    public List<EtageBriefDTO> getEmptySpotsBrief() {
        String query = """
                    query EmptySpots {
                        emptySpots {
                            label
                            hasPaymentKiosk
                            spots {
                                code
                                type
                                isOccupied
                            }
                        }
                    }
                """;

        // The result is a List<Etage>, so we use .toEntityList(Etage.class)
        List<EtageBriefDTO> result = graphQlClient.document(query)
                .retrieve("emptySpots")
                .toEntityList(EtageBriefDTO.class)
                .block();
        return result;
    }
/*
    public List<Etage> getAllEtages() {
        String query = """
                    query AllEtages {
                        getAllEtages {
                            id
                            label
                            capacity
                            hasPaymentKiosk
                            spots {
                                id
                                code
                                type
                                isOccupied
                            }
                        }
                    }
                """;

        // The result is a List<Etage>
        List<Etage> result = graphQlClient.document(query)
                .retrieve("getAllEtages")
                .toEntityList(Etage.class)
                .block();
        LG.info("Received {} Etages from Parking Service.", result != null ? result.size() : 0);

        return result;
    }

    public List<ReservationData> getAllReservations() {
        String query = """
                    query AllReservations {
                        getAllResrervations {
                            spotCode
                            etageLabel
                            durationInHours
                            refClient
                        }
                    }
                """;

        // The result is a List<ReservationData>
        List<ReservationData> result = graphQlClient.document(query)
                .retrieve("getAllResrervations")
                .toEntityList(ReservationData.class)
                .block();
        LG.info("Received {} total reservations from Parking Service.", result != null ? result.size() : 0);

        return result;
    }

    public List<ReservationData> getReservationsByRefClient(String refClient) {
        String query = """
                    query ReservationsByClient($refClient: String!) {
                        getReservationsByRefClient(refClient: $refClient) {
                            spotCode
                            etageLabel
                            durationInHours
                            refClient
                        }
                    }
                """;

        // The result is a List<ReservationData>
        List<ReservationData> result = graphQlClient.document(query)
                .variable("refClient", refClient) // Bind the input variable
                .retrieve("getReservationsByRefClient")
                .toEntityList(ReservationData.class)
                .block();
        LG.info("Received {} reservations for client {} from Parking Service.",
                result != null ? result.size() : 0, refClient);

        return result;
    }

    public Spot getSpotByCode(String codeSpot, String labelEtage) {
        String query = """
                    query SpotByCode($codeSpot: String!, $labelEtage: String!) {
                        spotByCode(codeSpot: $codeSpot, labelEtage: $labelEtage) {
                            id
                            code
                            type
                            isOccupied
                        }
                    }
                """;

        // The result is a single Spot
        Spot result = graphQlClient.document(query)
                .variable("codeSpot", codeSpot)   // Bind the first input variable
                .variable("labelEtage", labelEtage) // Bind the second input variable
                .retrieve("spotByCode")
                .toEntity(Spot.class)
                .block();
        LG.info("Received Spot details for code {}/{} from Parking Service.", codeSpot, labelEtage);

        return result;
    }

    public Etage getEtageByLabel(String labelEtage) {
        String query = """
                    query EtageByLabel($labelEtage: String!) {
                        EtageByLabel(labelEtage: $labelEtage) {
                            id
                            label
                            capacity
                            hasPaymentKiosk
                            spots {
                                id
                                code
                                type
                                isOccupied
                            }
                        }
                    }
                """;

        // The result is a single Etage
        Etage result = graphQlClient.document(query)
                .variable("labelEtage", labelEtage) // Bind the input variable
                .retrieve("EtageByLabel")
                .toEntity(Etage.class)
                .block();
        LG.info("Received Etage details for label {} from Parking Service.", labelEtage);

        return result;
    }
    */

}
