package com.example.aviasales.repositories;

import com.example.aviasales.Domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Flight findFlightById(Integer id);
    List<Flight> findAllByName(String name);
}
