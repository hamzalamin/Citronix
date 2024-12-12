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

public class FieldTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/create-field");
    }

    @Test
    public void create_ThrowInsufficientFarmSurfaceException(){
        WebElement nameInput = driver.findElement(By.name("name"));
        WebElement surfaceInput = driver.findElement(By.name("surface"));
        WebElement farmIdInput = driver.findElement(By.name("farmId"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        nameInput.sendKeys("Field Zan Zan " + System.currentTimeMillis());
        surfaceInput.sendKeys("100");
        farmIdInput.sendKeys("702");

        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-msg")));
        assertEquals("Field must be under 50% of the farm surface", errMessage.getText());
    }


    @AfterEach
    public void clean(){
        if (driver != null){
            driver.quit();
        }
    }

}
