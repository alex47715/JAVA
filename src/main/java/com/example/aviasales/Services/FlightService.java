package com.example.aviasales.Services;

import com.example.aviasales.Domain.Flight;
import com.example.aviasales.Domain.Role;
import com.example.aviasales.Domain.User;
import com.example.aviasales.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAll() { return flightRepository.findAll(); }

    public List<Flight> getAllByCategory(String category) { return flightRepository.findAllByName(category); }

    public Flight findById(Integer id) { return flightRepository.findFlightById(id); }

    public Flight addFlight(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }
}
