package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
