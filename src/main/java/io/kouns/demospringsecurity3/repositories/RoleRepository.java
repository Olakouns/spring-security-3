package io.kouns.demospringsecurity3.repositories;

import io.kouns.demospringsecurity3.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String userRole);
}
