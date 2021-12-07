package com.example.aviasales.Services;

import com.example.aviasales.Domain.User;
import com.example.aviasales.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public User findById(Integer id) { return userRepository.findUserById(id); }
}
