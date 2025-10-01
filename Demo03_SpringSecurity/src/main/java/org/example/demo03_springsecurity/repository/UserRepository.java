package org.example.demo03_springsecurity.repository;

import org.example.demo03_springsecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users getUserByUsername(@Param("username") String username);

    Users findByEmail(String email);

    Optional<Users> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    Users findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
