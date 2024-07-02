package com.pawsoncall.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pawsoncall.web.domain.ServiceProvider;
import com.pawsoncall.web.mapper.ServiceProviderService;
import java.util.List;

@RestController
@RequestMapping("/api/serviceproviders")
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @GetMapping
    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderService.getAllServiceProviders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceProvider> getServiceProviderById(@PathVariable Long id) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);
        return serviceProvider != null ? ResponseEntity.ok(serviceProvider)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ServiceProvider createServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        return serviceProviderService.saveServiceProvider(serviceProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceProvider> updateServiceProvider(@PathVariable Long id,
            @RequestBody ServiceProvider serviceProviderDetails) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);
        if (serviceProvider != null) {
            return ResponseEntity
                    .ok(serviceProviderService.saveServiceProvider(serviceProviderDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable Long id) {
        serviceProviderService.deleteServiceProvider(id);
        return ResponseEntity.noContent().build();
    }
}
