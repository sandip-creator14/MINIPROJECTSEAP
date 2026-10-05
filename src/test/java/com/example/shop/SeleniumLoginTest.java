package com.example.shop;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumLoginTest extends BaseSeleniumTest {
    @Test public void loginShowsProfile() {
        driver.get(BASE + "login");
        driver.findElement(By.id("email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("password")).sendKeys("demo123");
        driver.findElement(By.id("loginBtn")).click();

        wait.until(ExpectedConditions.urlContains("/profile"));
        assertEquals("Demo User",
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("p-name"))).getText());
        assertEquals("demo@gmail.com", driver.findElement(By.id("p-email")).getText());
    }

    @Test public void wrongPasswordShowsError() {
        driver.get(BASE + "login");
        driver.findElement(By.id("email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("password")).sendKeys("bad");
        driver.findElement(By.id("loginBtn")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"), "Invalid email or password"));
    }
}