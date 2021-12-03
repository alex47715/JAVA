package com.example.aviasales.Domain;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Order extends BaseModel {
    @Column(name = "user")
    private Integer user_id;
    @Column(name = "flight_id")
    private Integer flight_id;

    public Order() {
    }

    public Order(Integer user_id, Integer flight_id) {
        this.user_id = user_id;
        this.flight_id = flight_id;
    }
}
