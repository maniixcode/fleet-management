package com.fleet.mani.service;

import com.fleet.mani.model.*;
import com.fleet.mani.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fleet.mani.model.DeliveryStatus;
import java.util.List;

@Service
public class ManifestService {

    @Autowired
    private DeliveryTaskRepository taskRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RouteRepository routeRepository;

    // Assign driver and vehicle to task
    public DeliveryTask assignManifest(Long taskId, Long driverId,
                                       Long vehicleId, Long routeId) {
        DeliveryTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        task.setDriver(driver);
        task.setVehicle(vehicle);
        task.setRoute(route);
        task.setStatus(DeliveryStatus.DISPATCHED);

        // Update driver and vehicle status
        driver.setStatus("ON_TRIP");
        vehicle.setStatus("IN_USE");

        driverRepository.save(driver);
        vehicleRepository.save(vehicle);

        return taskRepository.save(task);
    }

    // Update delivery status
    public DeliveryTask updateStatus(Long taskId, DeliveryStatus newStatus) {
        DeliveryTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(newStatus);

        // If delivered, free up driver and vehicle
        if (newStatus == DeliveryStatus.DELIVERED) {
            if (task.getDriver() != null) {
                task.getDriver().setStatus("AVAILABLE");
                driverRepository.save(task.getDriver());
            }
            if (task.getVehicle() != null) {
                task.getVehicle().setStatus("AVAILABLE");
                vehicleRepository.save(task.getVehicle());
            }
            task.setCompletedTime(java.time.LocalDateTime.now());
        }

        return taskRepository.save(task);
    }

    // Get all manifests
    public List<DeliveryTask> getAllManifests() {
        return taskRepository.findAll();
    }
}