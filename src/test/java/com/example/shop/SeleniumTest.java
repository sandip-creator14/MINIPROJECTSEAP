package com.example.shop;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @Before
    public void setUp() {

        baseUrl = System.getProperty(
                "baseUrl",
                "http://localhost:8080/shopping-app/"
        );

        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


    // ---------------------------------------------------------
    // TEST 1: Products Page
    // ---------------------------------------------------------

    @Test
    public void testProductsPage() {

        driver.get(baseUrl + "products");

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-title, h1, h2")
                )
        );

        Assert.assertTrue(
                "Products heading should be displayed",
                title.getText().toLowerCase().contains("products")
        );
    }


    // ---------------------------------------------------------
    // TEST 2: Products Are Displayed
    // ---------------------------------------------------------

    @Test
    public void testProductsAreDisplayed() {

        driver.get(baseUrl + "products");

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".card, .product")
                )
        );

        List<WebElement> products = driver.findElements(
                By.cssSelector(".card, .product")
        );

        Assert.assertTrue(
                "Products should be displayed",
                products.size() > 0
        );
    }


    // ---------------------------------------------------------
    // TEST 3: Add To Cart
    // ---------------------------------------------------------

    @Test
    public void testAddToCart() {

        driver.get(baseUrl + "products");

        By addBtn = By.xpath(
                "//div[contains(@class,'card') or contains(@class,'product')]"
                        + "[1]//button[contains(normalize-space(),'Add to Cart')]"
        );

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(addBtn)
        );

        button.click();

        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.ok, .alert")
                )
        );

        Assert.assertTrue(
                "Add to Cart confirmation should be displayed",
                alert.getText().toLowerCase().contains("added")
        );
    }


    // ---------------------------------------------------------
    // TEST 4: Login Page
    // ---------------------------------------------------------

    @Test
    public void testLoginPage() {

        driver.get(baseUrl + "login");

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1, h2, .page-title")
                )
        );

        Assert.assertTrue(
                "Login heading should be displayed",
                heading.getText().toLowerCase().contains("login")
        );
    }


    // ---------------------------------------------------------
    // TEST 5: Navigation To Cart
    // ---------------------------------------------------------

    @Test
    public void testNavigationToCart() {

        driver.get(baseUrl);

        By cartLocator = By.cssSelector(
                "#nav-cart, a[href*='cart']"
        );

        boolean clicked = false;

        /*
         * The navigation bar can be refreshed/re-rendered by JavaScript.
         * Therefore, we locate nav-cart again immediately before clicking.
         * This prevents StaleElementReferenceException.
         */

        for (int attempt = 0; attempt < 3 && !clicked; attempt++) {

            try {

                WebElement cartLink = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                cartLocator
                        )
                );

                cartLink.click();

                clicked = true;

            } catch (StaleElementReferenceException e) {

                // Re-find the element on the next attempt.
                System.out.println(
                        "Cart element became stale. Retrying..."
                );
            }
        }

        Assert.assertTrue(
                "Cart link should be clickable",
                clicked
        );

        WebElement cartHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1, h2, .page-title")
                )
        );

        Assert.assertTrue(
                "Cart page should be displayed",
                cartHeading.getText()
                        .toLowerCase()
                        .contains("cart")
        );
    }


    // ---------------------------------------------------------
    // TEST 6: Create Account Link
    // ---------------------------------------------------------

    @Test
    public void testCreateAccountLink() {

        driver.get(baseUrl + "login");

        WebElement registerLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//a[contains(normalize-space(),'Create Account')"
                                        + " or contains(normalize-space(),'Create an account')"
                                        + " or @id='create-account-link']"
                        )
                )
        );

        Assert.assertNotNull(
                "Create Account link should exist",
                registerLink
        );

        Assert.assertTrue(
                "Create Account link should be visible",
                registerLink.isDisplayed()
        );
    }
}

