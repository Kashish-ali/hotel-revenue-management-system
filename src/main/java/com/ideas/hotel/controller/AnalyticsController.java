package com.ideas.hotel.controller;

import com.ideas.hotel.model.DashboardStats;
import com.ideas.hotel.model.PriceRecommendation;
import com.ideas.hotel.service.PricingService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin
public class AnalyticsController {
    private final JdbcTemplate jdbc; private final PricingService pricing;
    public AnalyticsController(JdbcTemplate jdbc, PricingService pricing) { this.jdbc=jdbc; this.pricing=pricing; }
    @GetMapping("/dashboard")
    public DashboardStats dashboard() {
        double revenue = jdbc.queryForObject("SELECT COALESCE(SUM(total_amount),0) FROM bookings WHERE status IN ('CONFIRMED','CHECKED_IN','COMPLETED')", Double.class);
        long bookings = jdbc.queryForObject("SELECT COUNT(*) FROM bookings", Long.class);
        long occupied = jdbc.queryForObject("SELECT COUNT(*) FROM rooms WHERE status='BOOKED'", Long.class);
        long rooms = jdbc.queryForObject("SELECT COUNT(*) FROM rooms", Long.class);
        return new DashboardStats(revenue, bookings, occupied, rooms, rooms == 0 ? 0 : occupied * 100.0 / rooms);
    }
    @GetMapping("/pricing") public List<PriceRecommendation> pricing() { return pricing.recommendations(); }
}
