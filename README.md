# Orchestrator Service (Service Orchestrateur)

> [!NOTE]
> This service is part of a microservice orcherstration project. For full details, please visit the **[Smart City Microservices Project](https://github.com/Kossay-Zemzem/Smart-City-Microservices)** main repository.

## Service description

The Orchestrator Service is a component of the Smart City platform that coordinates business workflows spanning several services. The core services expose different communication styles (REST, SOAP, and GraphQL), so the orchestrator acts as an integration layer that hides these differences from client applications. It chains the necessary calls (for example, creating a parking reservation and obtaining the associated invoice) and aggregates the results into a single response.

## Tech Stack

* **Framework:** Spring Boot
* **API Style:** REST
* **Database:** H2

> [!IMPORTANT]
> The orchestrator intentionally includes generated sources in the `target` folder to facilitate demonstration and testing. **Generated sources are normally not versioned**, but an exception was made here because generating SOAP client stubs correctly can be tricky. These sources include the SOAP client stubs produced from the [Billing Service's WSDL specification](https://github.com/Kossay-Zemzem/Smart-City-Microservices/blob/main/specs/Facture-service.wsdl), which the Orchestrator needs in order to communicate with that service.
>
> Do not run `mvn clean` on the Orchestrator, as this deletes the `target` folder along with these sources. If you do you will have to regenerate them using the [wsimport command-line tool](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/wsimport.html)
