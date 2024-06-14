package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
