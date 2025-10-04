package org.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.jwt.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
