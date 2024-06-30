package com.pawsoncall.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pawsoncall.web.domain.ServiceProvider;
import java.util.List;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository providerRepository;

    public List<ServiceProvider> getAllServiceProviders() {
        return providerRepository.findAll();
    }

    public ServiceProvider getServiceProviderById(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    public ServiceProvider saveServiceProvider(ServiceProvider provider) {
        return providerRepository.save(provider);
    }

    public void deleteServiceProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
