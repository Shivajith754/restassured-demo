package com.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.tests.BaseTest; // import your base

public class GoogleUiTest extends BaseTest {

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();
        // Print the actual title
        System.out.println("Page title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }
}
