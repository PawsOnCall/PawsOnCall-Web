package com.pawsoncall.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.pawsoncall.web.domain.Service;
import java.util.List;

@org.springframework.stereotype.Service
public class PawServiceService {
    @Autowired
    private PawServiceRepository serviceRepository;

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public Service saveService(Service product) {
        return serviceRepository.save(product);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
