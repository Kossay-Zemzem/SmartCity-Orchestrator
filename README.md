# Orchestrator Service (Service Orchestrateur)

> [!NOTE]
> This service is part of a microservice orcherstration project. For full details, please visit the **[Smart City Microservices Project](https://github.com/Kossay-Zemzem/Smart-City-Microservices)** main repository.

## Service description

The Orchestrator Service is a component of the Smart City platform that coordinates business workflows spanning several services. The core services expose different communication styles (REST, SOAP, and GraphQL), so the orchestrator acts as an integration layer that hides these differences from client applications. It chains the necessary calls (for example, creating a parking reservation and obtaining the associated invoice) and aggregates the results into a single response.

## Tech Stack

* **Framework:** Spring Boot
* **API Style:** REST
* **Database:** No database is used
