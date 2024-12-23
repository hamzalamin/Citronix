package com.wora.citronix.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FarmTest {

    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/create-farm");

    }


    //todo: Reusable Page Objects
    @Test
    public void createFarm_withUniqueName_shouldSucceed() {
        WebElement nameInput = driver.findElement(By.name("name"));
        WebElement localizationInput = driver.findElement(By.name("localization"));
        WebElement surfaceInput = driver.findElement(By.name("surface"));
        WebElement creationDateInput = driver.findElement(By.name("creationDate"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Farm Hamza not Lamin ZAN-ZAN"  + System.currentTimeMillis());
        localizationInput.sendKeys("AGADIR, TARRAST");
        surfaceInput.sendKeys("10.00");
        creationDateInput.sendKeys("12-12-2024");

        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-msg")));
        assertEquals("Farm created successfully.", successMessage.getText(), "Success message mismatch");

    }

    @Test
    public void createFarm_withNotUniqueName_ShouldThrowException(){
        WebElement nameInput = driver.findElement(By.name("name"));
        WebElement localizationInput = driver.findElement(By.name("localization"));
        WebElement surfaceInput = driver.findElement(By.name("surface"));
        WebElement creationDateInput = driver.findElement(By.name("creationDate"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Farm Hamza not Lamin ZAN-ZAN");
        localizationInput.sendKeys("AGADIR, TARRAST");
        surfaceInput.sendKeys("10.00");
        creationDateInput.sendKeys("12-12-2024");
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-msg")));
        String errorText = errorMessage.getText();
        assertTrue(errorText.contains("unexpected error") || errorText.contains("unique constraint"));
    }

    @Test
    public void createFarm_withInvalidSurface_ShouldThrowException(){
        WebElement nameInput = driver.findElement(By.name("name"));
        WebElement localizationInput = driver.findElement(By.name("localization"));
        WebElement surfaceInput = driver.findElement(By.name("surface"));
        WebElement creationDateInput = driver.findElement(By.name("creationDate"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Farm Hamza not Lamin ZAN-ZAN" + System.currentTimeMillis());
        localizationInput.sendKeys("AGADIR, TARRAST");
        surfaceInput.sendKeys("0.1");
        creationDateInput.sendKeys("12-12-2024");
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-msg")));
        assertEquals("The min surface value of farm must be 0.2 Hectar", errorMessage.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}









