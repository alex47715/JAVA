package com.example.aviasales.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")

public class Order extends BaseModel {
    @Column(name = "flight_id")
    private Integer flightId;
    @Column(name = "count")
    private Integer count;
    @Column(name = "user_id")
    private Integer userId;

    public Order(Integer flight, Integer count, Integer user) {
        this.flightId = flight;
        this.count = count;
        this.userId = user;
    }
}
