package com.example.aviasales.Domain;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel {
    public Role() {
    }

    @Column(name = "name")
    private String name;
}
