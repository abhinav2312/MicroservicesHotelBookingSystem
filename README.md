MicroservicesHotelBookingSystem

This is code for hotel booking system with microservice architecture. More details about the architecture and implementation are available in the docs directory.

How to run
1. Clone the repository into your local system
2. Change the database details in the application.properties file
3. Try opening the project in your IDE and run all applications simultaneously

commands:

docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.2 start-dev

docker compose up -d

docker run -d -p 9411:9411 openzipkin/zipkin