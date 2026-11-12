package com.demo.BookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.BookingSystem.entity.Venue;



public interface VenueRepository extends JpaRepository<Venue,Long> {

    Optional<Venue>  findByName(String name);
}
