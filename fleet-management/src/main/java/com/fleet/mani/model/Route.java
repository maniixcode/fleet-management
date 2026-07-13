package com.fleet.mani.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startLocation;
    private String endLocation;
    private Double distanceKm;
    private Integer estimatedTimeMinutes;
    //Coordinates for OSRM
    private Double startLat;
    private Double startLon;
    private Double endLat;
    private Double endLon;
}