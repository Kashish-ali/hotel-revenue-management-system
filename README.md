# Hotel Revenue Management System

A portfolio project designed around the Associate Software Engineer requirements from the IDeaS placement JD: Java/OOP, database fundamentals (SQL/NoSQL), JavaScript frontend, REST APIs, and quality/testing concepts. The business domain is hotel revenue management, matching IDeaS's hospitality revenue-optimization focus.

## Tech stack
- Java 17
- Spring Boot
- OOP (classes, encapsulation, services, domain models)
- JDBC / JdbcTemplate
- MySQL + SQL
- REST APIs
- HTML/CSS/JavaScript frontend
- Maven
- Git/GitHub
- Selenium + JUnit integration test

## Features
- Room and customer management
- Booking creation with date-overlap validation
- Revenue dashboard
- Occupancy calculation
- SQL joins and aggregate analytics
- Rule-based dynamic pricing based on occupancy
- REST endpoints
- Selenium smoke test for the web dashboard

## Database setup
1. Install MySQL.
2. Run `src/main/resources/db/schema.sql`.
3. Run `src/main/resources/db/seed.sql`.
4. Update `src/main/resources/application.properties`, or set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables.

Default configuration expects MySQL on localhost with username `root` and password `root`. Change it before running if needed.

## Run
```bash
mvn clean spring-boot:run
```
Open `http://localhost:8080`.

## Build
```bash
mvn clean package
```

## Selenium test
Start the application first, then run:
```bash
mvn -Dtest=HotelRevenueManagementSeleniumTest test
```
Selenium Manager will resolve ChromeDriver if Chrome/Chromium is installed.

## Interview topics demonstrated
- OOP and separation of responsibilities
- JDBC and prepared SQL parameters
- Primary/foreign keys and indexing
- JOIN, GROUP BY, aggregate functions
- Transactional booking creation
- REST API design
- Client-side fetch/API integration
- Basic Selenium UI testing
- Rule-based pricing and revenue analytics

## Honest resume note
Only claim technologies/features you can explain and demonstrate in the interview. If you add Spring Boot or Selenium to your resume, learn the relevant basics before the interview.
