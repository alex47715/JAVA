package com.example.aviasales.repositories;

import com.example.aviasales.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findByEmail(String email);
    <S extends User> S save(S s);
}
