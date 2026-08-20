package com.ideas.hotel.model;

public record PriceRecommendation(String roomType, double basePrice, double occupancyRate, double suggestedPrice, String reason) {}
