package com.example.aviasales.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")

public class Order extends BaseModel {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column(name = "count")
    private Integer count;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Flight flight, Integer count, User user) {
        this.flight = flight;
        this.count = count;
        this.user = user;
    }
}
