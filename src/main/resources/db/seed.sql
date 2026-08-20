USE hotel_revenue;
INSERT INTO customers(name,email,phone) VALUES
('Aarav Sharma','aarav@example.com','9876543210'),
('Riya Mehta','riya@example.com','9876543211'),
('Kabir Singh','kabir@example.com','9876543212')
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO rooms(room_number,room_type,base_price,status) VALUES
('101','Standard',3000,'AVAILABLE'),
('102','Standard',3000,'AVAILABLE'),
('201','Deluxe',5000,'BOOKED'),
('202','Deluxe',5000,'AVAILABLE'),
('301','Suite',8000,'AVAILABLE'),
('302','Suite',8000,'MAINTENANCE')
ON DUPLICATE KEY UPDATE base_price=VALUES(base_price), status=VALUES(status);

INSERT INTO bookings(customer_id,room_id,check_in,check_out,guests,total_amount,status)
SELECT c.id, r.id, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 2 DAY), 2, 10000, 'CONFIRMED'
FROM customers c JOIN rooms r ON r.room_number='201'
WHERE c.email='aarav@example.com'
AND NOT EXISTS (SELECT 1 FROM bookings b WHERE b.room_id=r.id AND b.check_in=CURDATE());
