# Fleet Management and Route Optimization Engine

## Overview
A Spring Boot REST API for managing vehicles, drivers, delivery tasks, and optimized delivery routes. The project integrates with the OSRM API to calculate efficient routes and uses MySQL for data storage.

## Features
- Vehicle Management
- Driver Management
- Route Management
- Delivery Task Management
- Route Optimization using OSRM API
- Swagger API Documentation
- Global Exception Handling
- Docker Support

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger (Springdoc OpenAPI)
- Maven
- Docker
- OSRM API

## Architecture
Client (Swagger/Postman)
↓
Controller
↓
Service
↓
Repository
↓
MySQL Database

RouteOptimizationService
↓
OSRM API

## API Modules
- Vehicle Controller
- Driver Controller
- Route Controller
- Delivery Task Controller
- Manifest Controller
- Route Optimization Controller

## How to Run
1. Clone the repository.
2. Configure MySQL in application.properties.
3. Run the Spring Boot application.
4. Open Swagger UI.
5. Test APIs using Swagger or Postman.
