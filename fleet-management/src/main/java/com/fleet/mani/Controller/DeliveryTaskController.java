package com.fleet.mani.controller;

import com.fleet.mani.model.DeliveryTask;
import com.fleet.mani.service.DeliveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class DeliveryTaskController {

    @Autowired
    private DeliveryTaskService deliveryTaskService;

    @GetMapping
    public List<DeliveryTask> getAllTasks() {
        return deliveryTaskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public DeliveryTask getTaskById(@PathVariable Long id) {
        return deliveryTaskService.getTaskById(id);
    }

    @PostMapping
    public DeliveryTask createTask(@RequestBody DeliveryTask task) {
        return deliveryTaskService.createTask(task);
    }

    @PutMapping("/{id}")
    public DeliveryTask updateTask(@PathVariable Long id, @RequestBody DeliveryTask task) {
        return deliveryTaskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        deliveryTaskService.deleteTask(id);
    }
}