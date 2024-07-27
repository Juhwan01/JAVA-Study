package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByNickname(String nickname);

    boolean existsByUsername(String email);
    boolean existsByNickname(String username);
    boolean existsByEmail(String email);
}
