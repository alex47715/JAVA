package com.example.aviasales.Controllers;

import com.example.aviasales.DTO.OrderDTO;
import com.example.aviasales.Domain.Flight;
import com.example.aviasales.Domain.Order;
import com.example.aviasales.Domain.User;
import com.example.aviasales.Services.FlightService;
import com.example.aviasales.Services.OrderService;
import com.example.aviasales.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    FlightService flightService;

    @PostMapping("add")
    public ResponseEntity addToCart(RequestEntity<OrderDTO> orderDTO) {
        User user = userService.findById(orderDTO.getBody().getAccountId());
        Flight flight = flightService.findById(orderDTO.getBody().getProductId());
        Order order = new Order(flight.getId(),orderDTO.getBody().getCount(),user.getId());
        orderService.createCartOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "cart")
    public ResponseEntity<List<Order>> getCart(@RequestParam Map<String, String> mapParam) {
        Integer userId = Integer.parseInt(mapParam.get("accountId"));
        User user = userService.findById(userId);
        List<Order> cart = orderService.getAccountProducts(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @GetMapping(value = "all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> cart = orderService.getAll();
        return new ResponseEntity(cart, HttpStatus.OK);
    }

    @PatchMapping(value = "updateCount")
    public ResponseEntity updateCartProductQuantity(@RequestParam Map<String, String> mapParam) {
        Integer cartOrderId = Integer.parseInt(mapParam.get("cartOrderId"));
        int count = Integer.parseInt(mapParam.get("quantity"));
        orderService.changeCartProductCount(cartOrderId, count);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
