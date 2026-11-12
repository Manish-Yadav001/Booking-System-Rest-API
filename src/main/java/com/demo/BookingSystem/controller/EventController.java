package com.demo.BookingSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.BookingSystem.dto.request.EventRequest;
import com.demo.BookingSystem.dto.response.EventResponse;
import com.demo.BookingSystem.service.EventService;

import jakarta.validation.Valid;

@RestController 
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PostMapping("/api/events")
    public ResponseEntity<EventResponse> createEvent(@Valid@RequestBody EventRequest request){
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(eventService.createEvent(request));
    }

    @GetMapping("/api/events/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/api/events")
    public ResponseEntity<List<EventResponse>> getAllEvent(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PutMapping ("/api/events/{id}")
    public ResponseEntity <EventResponse> updateEvent(
        @PathVariable Long id,@Valid@RequestBody EventRequest request){
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }

    @DeleteMapping("/api/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
