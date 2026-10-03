package com.example.shop;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public abstract class BaseSeleniumTest {
    protected static WebDriver driver;
    protected static final String BASE = System.getProperty("baseUrl", "http://localhost:8080/shopping-app/");

    @BeforeClass public static void start() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1280,900");
        driver = new ChromeDriver(o);
    }
    @AfterClass public static void stop() { if (driver != null) driver.quit(); }
}
