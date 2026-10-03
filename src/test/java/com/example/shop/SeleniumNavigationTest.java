package com.example.shop;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class SeleniumNavigationTest extends BaseSeleniumTest {
    @Test public void navigateAndAddToCart() {
        driver.get(BASE);
        driver.findElement(By.id("nav-products")).click();
        assertTrue(driver.getCurrentUrl().contains("/products"));
        driver.findElement(By.cssSelector(".card form button")).click();
        driver.findElement(By.id("nav-cart")).click();
        assertTrue(driver.getPageSource().contains("₹"));
        assertTrue(driver.findElement(By.id("nav-cart")).getText().contains("(1)"));
    }
}
