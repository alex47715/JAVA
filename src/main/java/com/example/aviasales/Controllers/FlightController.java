package com.example.aviasales.Controllers;

import com.example.aviasales.Domain.Flight;
import com.example.aviasales.Services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "all")
    public ResponseEntity<List<Flight>> getProducts() {
        List<Flight> products = flightService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping(value = "byid")
    public ResponseEntity<Flight> getProductById(@RequestParam Map<String, String> mapParam) {
        Flight products = flightService.findById(Integer.parseInt(mapParam.get("id")));
        return new ResponseEntity(products, HttpStatus.OK);
    }
}
