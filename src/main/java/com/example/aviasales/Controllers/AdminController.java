package com.example.aviasales.Controllers;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<List<Order>> getCart(@RequestBody User user) {
        User getUser = userService.findByLogin(user.getLogin());
        List<Order> cart = orderService.getAccountProducts(getUser);
        return new ResponseEntity(cart,HttpStatus.OK);
    }
    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> result = flightService.getAll();
        return new ResponseEntity(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/flights/add", method = RequestMethod.POST)
    public ResponseEntity<Flight> addFlights(@RequestBody Flight flight) {
        Flight result = flightService.addFlight(flight);
        return new ResponseEntity(result,HttpStatus.OK);
    }

}
