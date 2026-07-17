package com.fleet.mani.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OsrmService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String OSRM_BASE_URL = "http://router.project-osrm.org";

    public double getDistance(double fromLat, double fromLon,
                              double toLat, double toLon) {
        try {
            String url = String.format(
                    "%s/route/v1/driving/%f,%f;%f,%f?overview=false",
                    OSRM_BASE_URL, fromLon, fromLat, toLon, toLat
            );

            String response = restTemplate.getForObject(url, String.class);

            if (response != null) {
                int distanceIndex = response.indexOf("\"distance\":");
                if (distanceIndex != -1) {
                    String distStr = response.substring(
                            distanceIndex + 11,
                            response.indexOf(",", distanceIndex + 11)
                    );
                    double distanceMeters = Double.parseDouble(distStr.trim());
                    return distanceMeters / 1000.0;
                }
            }
        } catch (Exception e) {
            System.out.println("OSRM Error: " + e.getMessage());
        }
        return 0.0;
    }
}