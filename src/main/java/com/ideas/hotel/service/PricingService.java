package com.ideas.hotel.service;

import com.ideas.hotel.model.PriceRecommendation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {
    private final JdbcTemplate jdbc;
    public PricingService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<PriceRecommendation> recommendations() {
        long total = jdbc.queryForObject("SELECT COUNT(*) FROM rooms", Long.class);
        long occupied = jdbc.queryForObject("SELECT COUNT(*) FROM rooms WHERE status='BOOKED'", Long.class);
        double occupancy = total == 0 ? 0 : occupied * 100.0 / total;
        return jdbc.query("SELECT room_type, AVG(base_price) base_price FROM rooms GROUP BY room_type ORDER BY room_type", (rs,n) -> {
            double base = rs.getDouble("base_price");
            double multiplier = occupancy > 80 ? 1.20 : occupancy >= 50 ? 1.10 : 1.00;
            String reason = occupancy > 80 ? "High demand: occupancy above 80%" : occupancy >= 50 ? "Moderate demand: occupancy 50–80%" : "Low demand: occupancy below 50%";
            return new PriceRecommendation(rs.getString("room_type"), base, occupancy, Math.round(base * multiplier * 100.0) / 100.0, reason);
        });
    }
}
