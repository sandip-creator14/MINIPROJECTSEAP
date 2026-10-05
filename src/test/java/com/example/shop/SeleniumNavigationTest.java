package com.example.shop;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumNavigationTest extends BaseSeleniumTest {
    @Test
    public void navigateAndAddToCart() {
        driver.get(BASE);
        pause();

        clickable(By.cssSelector("#nav-products, a[href*='products']")).click();
        wait.until(ExpectedConditions.urlContains("/products"));
        assertTrue(driver.getCurrentUrl().contains("/products"));
        pause();

        // Add first product (form submit reloads the page)
        clickable(By.cssSelector(".card form button")).click();
        pause();

        // Wait for the nav bar to render on the new page, then open cart
        clickable(CART_LINK).click();
        wait.until(ExpectedConditions.urlContains("cart"));
        assertTrue(driver.getPageSource().contains("₹"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(CART_LINK, "(1)"));
        pause();
    }
}