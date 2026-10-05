package com.example.shop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseSeleniumTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final String BASE = normalize(
            System.getProperty("baseUrl", "http://localhost:8080/shopping-app/"));

    // Browser is VISIBLE by default. Use -Dheadless=true for CI/servers.
    private static final boolean HEADLESS = Boolean.getBoolean("headless");
    // Delay (ms) between steps so you can watch: -DslowMs=1500
    private static final long SLOW_MS = Long.getLong("slowMs", 800L);

    protected static final By CART_LINK = By.cssSelector("#nav-cart, a[href*='cart']");

    private static String normalize(String url) {
        return url.endsWith("/") ? url : url + "/";
    }

    @BeforeClass
    public static void start() {
        System.out.println(">>> HEADLESS = " + HEADLESS);
        ChromeOptions o = new ChromeOptions();
        if (HEADLESS) {
            o.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--disable-gpu");
        }
        o.addArguments("--window-size=1280,900");
        driver = new ChromeDriver(o);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass
    public static void stop() {
        pause();
        if (driver != null) driver.quit();
    }

    /** Waits until the element is clickable, then returns it. */
    protected static WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /** Short pause so the test is watchable in a visible browser. */
    protected static void pause() {
        if (HEADLESS || SLOW_MS <= 0) return;
        try {
            Thread.sleep(SLOW_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}