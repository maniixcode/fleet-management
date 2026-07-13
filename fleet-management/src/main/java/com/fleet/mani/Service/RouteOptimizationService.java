package com.fleet.mani.service;

import com.fleet.mani.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteOptimizationService {

    @Autowired
    private OsrmService osrmService;

    // Greedy TSP Algorithm with OSRM distances
    public List<Route> optimizeRoutes(List<Route> routes) {
        if (routes == null || routes.isEmpty()) return routes;

        List<Route> optimized = new ArrayList<>();
        List<Route> remaining = new ArrayList<>(routes);

        Route current = remaining.remove(0);
        optimized.add(current);

        while (!remaining.isEmpty()) {
            Route nearest = findNearest(current, remaining);
            optimized.add(nearest);
            remaining.remove(nearest);
            current = nearest;
        }

        return optimized;
    }

    private Route findNearest(Route current, List<Route> routes) {
        Route nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Route route : routes) {
            double distance;

            // Use OSRM if coordinates available
            if (current.getEndLat() != null && current.getEndLon() != null
                    && route.getStartLat() != null && route.getStartLon() != null) {
                distance = osrmService.getDistance(
                        current.getEndLat(), current.getEndLon(),
                        route.getStartLat(), route.getStartLon()
                );
            } else {
                // Fallback to stored distance
                distance = route.getDistanceKm() != null ? route.getDistanceKm() : 0;
            }

            if (distance < minDistance) {
                minDistance = distance;
                nearest = route;
            }
        }
        return nearest;
    }
}