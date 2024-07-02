package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
