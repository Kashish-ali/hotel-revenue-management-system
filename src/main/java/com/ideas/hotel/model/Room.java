package com.ideas.hotel.model;

public class Room {
    private Long id;
    private String roomNumber;
    private String roomType;
    private double basePrice;
    private String status;

    public Room() {}
    public Room(Long id, String roomNumber, String roomType, double basePrice, String status) {
        this.id = id; this.roomNumber = roomNumber; this.roomType = roomType; this.basePrice = basePrice; this.status = status;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
