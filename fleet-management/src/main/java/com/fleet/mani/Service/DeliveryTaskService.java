package com.fleet.mani.service;

import com.fleet.mani.model.DeliveryTask;
import com.fleet.mani.repository.DeliveryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryTaskService {

    @Autowired
    private DeliveryTaskRepository deliveryTaskRepository;

    public List<DeliveryTask> getAllTasks() {
        return deliveryTaskRepository.findAll();
    }

    public DeliveryTask getTaskById(Long id) {
        return deliveryTaskRepository.findById(id).orElse(null);
    }

    public DeliveryTask createTask(DeliveryTask task) {
        return deliveryTaskRepository.save(task);
    }

    public DeliveryTask updateTask(Long id, DeliveryTask task) {
        task.setId(id);
        return deliveryTaskRepository.save(task);
    }

    public void deleteTask(Long id) {
        deliveryTaskRepository.deleteById(id);
    }
}