package com.ideas.hotel;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Run with the Spring Boot app already running on localhost:8080. */
class HotelRevenueManagementSeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options); // Selenium Manager resolves the driver.
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }

    @Test
    void dashboardLoads() {
        driver.get("http://localhost:8080/");
        assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Hotel Revenue Management"));
        assertTrue(driver.findElement(By.id("revenue")).isDisplayed());
    }
}
