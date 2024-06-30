package com.pawsoncall.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pawsoncall.web.domain.Service;
import com.pawsoncall.web.mapper.PawServiceService;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class PawServiceController {
    @Autowired
    private PawServiceService svcService;

    @GetMapping
    public List<Service> getAllServices() {
        return svcService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Service order = svcService.getServiceById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Service createService(@RequestBody Service order) {
        return svcService.saveService(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id,
            @RequestBody Service orderDetails) {
        Service order = svcService.getServiceById(id);
        if (order != null) {
            return ResponseEntity.ok(svcService.saveService(orderDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        svcService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
