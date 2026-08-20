package com.ideas.hotel.controller;

import com.ideas.hotel.model.Room;
import com.ideas.hotel.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {
    private final RoomRepository repo;
    public RoomController(RoomRepository repo) { this.repo = repo; }
    @GetMapping public List<Room> all() { return repo.findAll(); }
}
