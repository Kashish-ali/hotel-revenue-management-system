package com.ideas.hotel.controller;

import com.ideas.hotel.model.Customer;
import com.ideas.hotel.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo) { this.repo=repo; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody Customer c) { return Map.of("id", repo.create(c), "message", "Customer created successfully"); }
}
