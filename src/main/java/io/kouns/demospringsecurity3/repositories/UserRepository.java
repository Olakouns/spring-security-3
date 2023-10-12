package io.kouns.demospringsecurity3.repositories;

import io.kouns.demospringsecurity3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailOrUsername(String email, String username);
}
