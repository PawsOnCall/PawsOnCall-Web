package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Service;

public interface PawServiceRepository extends JpaRepository<Service, Long> {
}
