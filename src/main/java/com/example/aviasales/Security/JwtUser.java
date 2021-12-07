package com.example.aviasales.Security;

import com.example.aviasales.Domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class JwtUser implements UserDetails {
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static JwtUser fromAccountToJwtAccount(User account) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.login = account.getLogin();
        jwtUser.password = account.getPassword();
        jwtUser.authorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getName()));

        return jwtUser;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
