package com.demo.BookingSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "events")
public class Event {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "event_id")
    private Long id;
    @Column (name = "event_title",nullable=false,length = 150)
    private String title;
    @Column (name = "event_description",length = 300)
    private String description;
    @Column (name = "event_date",nullable=false)
    private LocalDateTime date;
    @Column (name = "event_price",nullable=false)
    private Long price;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "venue_id",nullable = false)
    private Venue venue;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    
    


}
