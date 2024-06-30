package com.pawsoncall.web.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pawsoncall.web.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);

    Boolean existsByEmail(String email);

    User findByFirstNameAndLastName(String firstName, String lastName);
}
