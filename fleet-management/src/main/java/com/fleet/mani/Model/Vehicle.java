package com.fleet.mani.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private String type; // TRUCK, VAN, BIKE
    private String status; // AVAILABLE, IN_USE, MAINTENANCE
    private Double capacity;
}