package io.kouns.demospringsecurity3.repositories;

import io.kouns.demospringsecurity3.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
