package com.ideas.hotel.repository;

import com.ideas.hotel.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private final JdbcTemplate jdbc;
    public CustomerRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public long create(Customer c) {
        jdbc.update("INSERT INTO customers(name,email,phone) VALUES(?,?,?)", c.getName(), c.getEmail(), c.getPhone());
        return jdbc.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }
    public boolean exists(long id) {
        Integer n = jdbc.queryForObject("SELECT COUNT(*) FROM customers WHERE id=?", Integer.class, id);
        return n != null && n > 0;
    }
}
