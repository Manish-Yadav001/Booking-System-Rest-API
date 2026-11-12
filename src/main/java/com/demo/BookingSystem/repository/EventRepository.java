package com.demo.BookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.demo.BookingSystem.entity.Event;

public interface EventRepository extends JpaRepository<Event,Long>{



}
