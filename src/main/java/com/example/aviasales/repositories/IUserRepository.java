package com.example.aviasales.repositories;

import com.example.aviasales.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    User findUserById(Integer id);
    User findByEmail(String email);
}
