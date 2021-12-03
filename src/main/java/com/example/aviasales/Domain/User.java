package com.example.aviasales.Domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@Getter
public class User extends BaseModel {

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id",referencedColumnName = "id"))
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String login, String password, String email, Set<Order> orders, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.email = email;
        orders = orders;
        roles = roles;
    }

    public User() {
    }
}
