package com.fleet.mani.controller;

import com.fleet.mani.model.Route;
import com.fleet.mani.service.RouteOptimizationService;
import com.fleet.mani.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/optimize")
public class RouteOptimizationController {

    @Autowired
    private RouteOptimizationService optimizationService;

    @Autowired
    private RouteService routeService;

    @GetMapping
    public List<Route> getOptimizedRoutes() {
        List<Route> allRoutes = routeService.getAllRoutes();
        return optimizationService.optimizeRoutes(allRoutes);
    }
}