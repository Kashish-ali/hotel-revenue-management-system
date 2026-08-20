package com.ideas.hotel.controller;

import com.ideas.hotel.model.Booking;
import com.ideas.hotel.repository.BookingRepository;
import com.ideas.hotel.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {
    private final BookingRepository repo; private final BookingService service;
    public BookingController(BookingRepository repo, BookingService service) { this.repo=repo; this.service=service; }
    @GetMapping public List<Booking> all() { return repo.findAll(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody Booking booking) { return Map.of("id", service.create(booking), "message", "Booking created successfully"); }
}
