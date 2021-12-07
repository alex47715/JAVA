package com.example.aviasales.Services;

import com.example.aviasales.Domain.Role;
import com.example.aviasales.Domain.User;
import com.example.aviasales.repositories.IRoleRepository;
import com.example.aviasales.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    IUserRepository userRepository;
    IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(IUserRepository userRepository,
                       IRoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(User account) {
        Role role = roleRepository.findRoleByName("ROLE_USER");
        account.setRole(role);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userRepository.save(account);
        return account;
    }
}
