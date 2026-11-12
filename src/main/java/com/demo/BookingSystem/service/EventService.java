package com.demo.BookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.BookingSystem.dto.request.EventRequest;
import com.demo.BookingSystem.dto.response.EventResponse;
import com.demo.BookingSystem.entity.Event;
import com.demo.BookingSystem.entity.Venue;
import com.demo.BookingSystem.exception.GlobalException;
import com.demo.BookingSystem.repository.EventRepository;
import com.demo.BookingSystem.repository.VenueRepository;

@Service 
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }
    
    private Event toEntity(EventRequest request,Venue venue){
        Event event=new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setPrice(request.getPrice());
        event.setVenue(venue);
        return event;
    }
    private EventResponse toResponse(Event event){
        EventResponse response=new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setDescription(event.getDescription());
        response.setPrice(event.getPrice());
        response.setVenueId(event.getVenue().getId());
        response.setVenueName(event.getVenue().getName());
        response.setDate(event.getDate());
        return response;
    }

    @Transactional 
    public EventResponse createEvent(EventRequest request){
        Venue venue = venueRepository.findById(request.getVenueId()).orElseThrow(() ->
                new GlobalException.ResourceNotFoundException(
                    "Venue doesn't exist with this id : )" + request.getVenueId()));
        Event event=toEntity(request,venue);
        Event savedEvent=eventRepository.save(event);
        return toResponse(savedEvent);
    }

    @Transactional (readOnly = true)
    public EventResponse getEventById(Long id){
        Optional <Event> existEvent=eventRepository.findById(id);
        if(existEvent.isEmpty()){
            throw new GlobalException.ResourceNotFoundException(
                "Event Doesn't Exist with Id : "+id);
        }
        return toResponse(existEvent.get());
    }

    @Transactional 
    public EventResponse updateEvent(Long id,EventRequest request){
        Event existEvent=eventRepository.findById(id)
        .orElseThrow(()
        -> new GlobalException.ResourceNotFoundException("Event Doesn't Exist : "+id));

        Venue venue = venueRepository.findById(request.getVenueId()).orElseThrow(() ->
                new GlobalException.ResourceNotFoundException(
                    "Venue doesn't exist with this id : )" + request.getVenueId()));

        existEvent.setTitle(request.getTitle());
        existEvent.setDescription(request.getDescription());
        existEvent.setPrice(request.getPrice());
        existEvent.setDate(request.getDate());
        existEvent.setVenue(venue);
        return toResponse(existEvent);
    }

    @Transactional (readOnly = true)
    public List<EventResponse> getAllEvents(){
        List<Event> events=eventRepository.findAll();
        return events.stream()
        .map(this::toResponse).toList();
    }

    @Transactional 
    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new GlobalException.ResourceNotFoundException(
                    "Event doesn't exist with this id : )" + id));

                    eventRepository.delete(event);
    }
}
