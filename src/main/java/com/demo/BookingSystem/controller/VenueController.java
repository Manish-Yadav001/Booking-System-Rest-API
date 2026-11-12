package com.demo.BookingSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.BookingSystem.dto.request.VenueRequest;
import com.demo.BookingSystem.dto.response.VenueResponse;
import com.demo.BookingSystem.service.VenueService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController 
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
        
    }
    
    @PostMapping("/api/venues")
    public ResponseEntity <VenueResponse>createVenue(@Valid @RequestBody VenueRequest venueRequest ){
    return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(venueService.createVenue(venueRequest));
    }
    
    @GetMapping("/api/venues")
    public ResponseEntity<List<VenueResponse>> getAllVenues(){

        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @GetMapping("/api/venues/{id}")
    public ResponseEntity<VenueResponse> getVenueById(@PathVariable Long id){

        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @PutMapping("/api/venues/{id}")
    public ResponseEntity<VenueResponse> updateVenue(@PathVariable Long id,
    @Valid  @RequestBody VenueRequest venueRequest
    ){
        return ResponseEntity
                .ok(venueService.updateVenue(id,venueRequest));
    }
    
    @DeleteMapping("/api/venues/{id}")
    public  ResponseEntity<Void> deleteVenue(@PathVariable Long id){

        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }

}
