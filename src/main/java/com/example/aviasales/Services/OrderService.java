package com.example.aviasales.Services;

import com.example.aviasales.Domain.Order;

import com.example.aviasales.Domain.User;
import com.example.aviasales.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepository orderRepository;

    public Order createCartOrder(Order cartOrder) {
        orderRepository.save(cartOrder);
        return cartOrder;
    }
    public List<Order> getAll(){ return orderRepository.findAll(); }
    public List<Order> getAccountProducts(User user) {
        return orderRepository.findOrdersByUserId(user.getId());
    }
    public boolean changeCartProductCount(Integer cartOrderId, int quantity) {
        Order cartOrder = orderRepository.findOrderById(cartOrderId);
        cartOrder.setCount(quantity);
        orderRepository.save(cartOrder);
        return true;
    }
}
