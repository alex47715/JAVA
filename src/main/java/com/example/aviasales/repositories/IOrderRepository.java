package com.example.aviasales.repositories;

import com.example.aviasales.Domain.Order;
import com.example.aviasales.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUserId(Integer id);
    Order findOrderById(Integer id);
}
