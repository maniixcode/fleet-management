package com.fleet.mani.service;

import com.fleet.mani.model.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteOptimizationService {

    // Greedy TSP Algorithm
    public List<Route> optimizeRoutes(List<Route> routes) {
        if (routes == null || routes.isEmpty()) return routes;

        List<Route> optimized = new ArrayList<>();
        List<Route> remaining = new ArrayList<>(routes);

        // Start from first route
        Route current = remaining.remove(0);
        optimized.add(current);

        // Always pick nearest next route
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
            double distance = route.getDistanceKm();
            if (distance < minDistance) {
                minDistance = distance;
                nearest = route;
            }
        }
        return nearest;
    }
}