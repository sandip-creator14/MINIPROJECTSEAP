package com.example.shop;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class SeleniumLoginTest extends BaseSeleniumTest {
    @Test public void loginShowsProfile() {
        driver.get(BASE + "login");
        driver.findElement(By.id("email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("password")).sendKeys("demo123");
        driver.findElement(By.id("loginBtn")).click();
        assertTrue(driver.getCurrentUrl().contains("/profile"));
        assertEquals("Demo User", driver.findElement(By.id("p-name")).getText());
        assertEquals("demo@gmail.com", driver.findElement(By.id("p-email")).getText());
    }
    @Test public void wrongPasswordShowsError() {
        driver.get(BASE + "login");
        driver.findElement(By.id("email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("password")).sendKeys("bad");
        driver.findElement(By.id("loginBtn")).click();
        assertTrue(driver.getPageSource().contains("Invalid email or password"));
    }
}
