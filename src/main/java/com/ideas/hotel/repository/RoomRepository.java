package com.ideas.hotel.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ideas.hotel.model.Room;

@Repository
public class RoomRepository {
    private final JdbcTemplate jdbc;
    public RoomRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Room> findAll() {
        return jdbc.query("SELECT id, room_number, room_type, base_price, status FROM rooms ORDER BY room_number",
                (rs, n) -> new Room(rs.getLong("id"), rs.getString("room_number"), rs.getString("room_type"), rs.getDouble("base_price"), rs.getString("status")));
    }
    public Room findById(long id) {
        return jdbc.queryForObject("SELECT id, room_number, room_type, base_price, status FROM rooms WHERE id=?",
                (rs, n) -> new Room(rs.getLong("id"), rs.getString("room_number"), rs.getString("room_type"), rs.getDouble("base_price"), rs.getString("status")), id);
    }
    public void updateStatus(long id, String status) {
        jdbc.update("UPDATE rooms SET status=? WHERE id=?", status, id);
    }
}
