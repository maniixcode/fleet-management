package com.fleet.mani.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OsrmService {

    private final WebClient webClient;

    public OsrmService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://router.project-osrm.org")
                .build();
    }

    // Get distance between two coordinates in km
    public double getDistance(double fromLat, double fromLon,
                              double toLat, double toLon) {
        try {
            String url = String.format(
                    "/route/v1/driving/%f,%f;%f,%f?overview=false",
                    fromLon, fromLat, toLon, toLat
            );

            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse distance from response
            int distanceIndex = response.indexOf("\"distance\":");
            if (distanceIndex != -1) {
                String distStr = response.substring(
                        distanceIndex + 11,
                        response.indexOf(",", distanceIndex + 11)
                );
                double distanceMeters = Double.parseDouble(distStr.trim());
                return distanceMeters / 1000.0; // Convert to km
            }
        } catch (Exception e) {
            System.out.println("OSRM Error: " + e.getMessage());
        }
        return 0.0;
    }
}