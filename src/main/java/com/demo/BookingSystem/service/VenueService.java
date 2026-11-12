package com.demo.BookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.BookingSystem.dto.request.VenueRequest;
import com.demo.BookingSystem.dto.response.VenueResponse;
import com.demo.BookingSystem.entity.Venue;
import com.demo.BookingSystem.exception.GlobalException;
import com.demo.BookingSystem.repository.VenueRepository;

@Service 
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }
    
    private Venue toEntity(VenueRequest request){
        Venue venue=new Venue();
        venue.setName(request.getName());
        venue.setLocation(request.getLocation());
        venue.setCapacity(request.getCapacity());
        return venue;
    }
    private VenueResponse toResponse(Venue venue){
        VenueResponse response=new VenueResponse();
        response.setId(venue.getId());
        response.setName(venue.getName());
        response.setLocation(venue.getLocation());
        response.setCapacity(venue.getCapacity());
        return response;
    }
    
    @Transactional 
    public VenueResponse createVenue(VenueRequest request){
        Optional<Venue> existVenue=venueRepository.findByName(request.getName());
        if(existVenue.isPresent()){
            throw new GlobalException.DuplicateResourceException(
                "Venue Already Exist With this Name : "+request.getName());
        }
        Venue venue= toEntity(request);
        Venue saveVenue=venueRepository.save(venue);
        return toResponse(saveVenue);
    }

    @Transactional(readOnly = true)
    public VenueResponse getVenueById(Long id){
        Optional<Venue> existVenue=venueRepository.findById(id);
        if (existVenue.isEmpty()) {
            throw new GlobalException.ResourceNotFoundException(
                    "Venue doesn't exist with this id : "
                            + id);
        }
        return toResponse(existVenue.get());
    }

    @Transactional 
    public VenueResponse updateVenue(Long id , VenueRequest request){
         Venue venue = venueRepository.findById(id).orElseThrow(() ->
                new GlobalException.ResourceNotFoundException(
                    "Venue doesn't exist with this id : )" + id));

        venue.setName(request.getName());
        venue.setCapacity(request.getCapacity());
        venue.setLocation(request.getLocation());
        Venue updateVenue=venueRepository.save(venue);
        return toResponse(updateVenue);
    }

     @Transactional(readOnly = true)
    public List<VenueResponse> getAllVenues(){
        List <Venue> venues=venueRepository.findAll();
        return venues.stream()
        .map(this::toResponse)
        .toList();
    }

    @Transactional 
    public void deleteVenue(Long id){
        Venue venue = venueRepository.findById(id).orElseThrow(() ->
                new GlobalException.ResourceNotFoundException(
                    "Venue doesn't exist with this id : )" + id));

                    venueRepository.delete(venue);
    }

}
