package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
