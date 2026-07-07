package com.fleet.mani.repository;

import com.fleet.mani.model.DeliveryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTaskRepository extends JpaRepository<DeliveryTask, Long> {
}