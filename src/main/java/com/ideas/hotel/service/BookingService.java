package com.ideas.hotel.service;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideas.hotel.model.Booking;
import com.ideas.hotel.model.Room;
import com.ideas.hotel.repository.BookingRepository;
import com.ideas.hotel.repository.CustomerRepository;
import com.ideas.hotel.repository.RoomRepository;

@Service
public class BookingService {
    private final BookingRepository bookings;
    private final RoomRepository rooms;
    private final CustomerRepository customers;

    public BookingService(BookingRepository bookings, RoomRepository rooms, CustomerRepository customers) {
        this.bookings = bookings; this.rooms = rooms; this.customers = customers;
    }

    @Transactional
    public long create(Booking booking) {
        if (booking.getCheckIn() == null || booking.getCheckOut() == null || !booking.getCheckOut().isAfter(booking.getCheckIn()))
            throw new IllegalArgumentException("Check-out must be after check-in.");
        if (booking.getGuests() <= 0) throw new IllegalArgumentException("Guests must be at least 1.");
        if (!customers.exists(booking.getCustomerId())) throw new IllegalArgumentException("Customer does not exist.");
        Room room = rooms.findById(booking.getRoomId());
        if ("MAINTENANCE".equals(room.getStatus())) throw new IllegalArgumentException("Room is under maintenance.");
        if (bookings.hasOverlap(booking.getRoomId(), booking.getCheckIn(), booking.getCheckOut())) throw new IllegalArgumentException("Room is already booked for the selected dates.");
        long nights = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        booking.setTotalAmount(nights * room.getBasePrice());
        booking.setStatus("CONFIRMED");
        long id = bookings.create(booking);
        rooms.updateStatus(booking.getRoomId(), "BOOKED");
        return id;
    }
}