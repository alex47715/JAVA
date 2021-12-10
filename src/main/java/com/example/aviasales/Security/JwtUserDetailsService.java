package com.example.aviasales.Security;

import com.example.aviasales.Domain.User;
import com.example.aviasales.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public JwtUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User account = userService.findByLogin(login);
        return JwtUser.fromAccountToJwtAccount(account);
    }
}
