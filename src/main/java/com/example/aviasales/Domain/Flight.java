package com.example.aviasales.Domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "flights")
@Data
@Getter
public class Flight extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "from_place")
    private String from_place;

    @Column(name = "to_place")
    private String to_place;

    @Column(name = "date_from")
    private Date date_from;

    @Column(name = "date_to")
    private Date date_to;
    @Column(name = "free")
    private int free;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id",referencedColumnName = "id"))
    private List<Order> orders;


    public Flight() {
    }

    public Flight(String name, Float price, String from_place, String to_place, Date date_from, Date date_to, int free) {
        this.name = name;
        this.price = price;
        this.from_place = from_place;
        this.to_place = to_place;
        this.date_from = date_from;
        this.date_to = date_to;
        this.free = free;
    }
}
