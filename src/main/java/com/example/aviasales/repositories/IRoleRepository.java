package com.example.aviasales.repositories;

import com.example.aviasales.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByLogin(String login);
}
