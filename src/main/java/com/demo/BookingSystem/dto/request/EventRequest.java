package com.demo.BookingSystem.dto.request;

import java.time.LocalDateTime;

import com.demo.BookingSystem.entity.Venue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EventRequest {
    @NotBlank 
    @Size(max = 150)
    private String title;
    @Size(max = 1000)
    private String description;
    @NotNull 
    private LocalDateTime date;
    @NotNull 
    @Positive 
    private Long price;
    @NotNull 
    private Long venueId;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getVenueId() {
        return venueId;
    }
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
    
    

    

}
