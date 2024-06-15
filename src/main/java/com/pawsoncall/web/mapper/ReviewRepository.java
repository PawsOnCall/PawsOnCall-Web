package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
