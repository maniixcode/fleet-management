package com.fleet.mani.controller;

import com.fleet.mani.model.DeliveryStatus;
import com.fleet.mani.model.DeliveryTask;
import com.fleet.mani.service.ManifestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manifest")
public class ManifestController {

    @Autowired
    private ManifestService manifestService;

    // Get all manifests
    @GetMapping
    public List<DeliveryTask> getAllManifests() {
        return manifestService.getAllManifests();
    }

    // Assign driver, vehicle, route to task
    @PostMapping("/assign")
    public DeliveryTask assignManifest(
            @RequestParam Long taskId,
            @RequestParam Long driverId,
            @RequestParam Long vehicleId,
            @RequestParam Long routeId) {
        return manifestService.assignManifest(taskId, driverId, vehicleId, routeId);
    }

    // Update delivery status
    @PutMapping("/status/{taskId}")
    public DeliveryTask updateStatus(
            @PathVariable Long taskId,
            @RequestParam DeliveryStatus status) {
        return manifestService.updateStatus(taskId, status);
    }
}