package com.demo.BookingSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity 
@Table(name="venues")
public class Venue {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="venue_id") 
    private Long id ;
    @Column(name="venue_name",nullable=false,length=100) 
    private String name;
    @Column(name="venue_location",nullable=false,length=250) 
    private String location;
    @Column(name="venue_capacity",nullable=false) 
    private Long capacity;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Long getCapacity() {
        return capacity;
    }
    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
    

    

}
