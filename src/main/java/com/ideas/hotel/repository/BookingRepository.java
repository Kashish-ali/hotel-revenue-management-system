package com.ideas.hotel.repository;

import com.ideas.hotel.model.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class BookingRepository {
    private final JdbcTemplate jdbc;
    public BookingRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Booking> findAll() {
        return jdbc.query("SELECT id, customer_id, room_id, check_in, check_out, guests, total_amount, status FROM bookings ORDER BY id DESC",
                (rs, n) -> map(rs));
    }

    public boolean hasOverlap(long roomId, java.time.LocalDate in, java.time.LocalDate out) {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM bookings WHERE room_id=? AND status IN ('CONFIRMED','CHECKED_IN') AND check_in < ? AND check_out > ?",
                Integer.class, roomId, Date.valueOf(out), Date.valueOf(in));
        return count != null && count > 0;
    }

    public long create(Booking b) {
        jdbc.update("INSERT INTO bookings(customer_id, room_id, check_in, check_out, guests, total_amount, status) VALUES(?,?,?,?,?,?,?)",
                b.getCustomerId(), b.getRoomId(), Date.valueOf(b.getCheckIn()), Date.valueOf(b.getCheckOut()), b.getGuests(), b.getTotalAmount(), b.getStatus());
        return jdbc.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    private Booking map(java.sql.ResultSet rs) throws java.sql.SQLException {
        Booking b = new Booking();
        b.setId(rs.getLong("id")); b.setCustomerId(rs.getLong("customer_id")); b.setRoomId(rs.getLong("room_id"));
        b.setCheckIn(rs.getDate("check_in").toLocalDate()); b.setCheckOut(rs.getDate("check_out").toLocalDate());
        b.setGuests(rs.getInt("guests")); b.setTotalAmount(rs.getDouble("total_amount")); b.setStatus(rs.getString("status"));
        return b;
    }
}
