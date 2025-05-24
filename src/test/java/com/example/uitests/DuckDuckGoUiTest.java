package com.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.tests.BaseTest; // import your base

public class DuckDuckGoUiTest extends BaseTest {

    @Test
    public void testDuckDuckGoSearch() {
        driver.get("https://duckduckgo.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        // Wait for the page title to contain "Selenium WebDriver" (or "at DuckDuckGo")
        wait.until(ExpectedConditions.titleContains("Selenium WebDriver"));
        // OR: wait until results are visible (DuckDuckGo uses this class for main results)
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".results--main")));

        System.out.println("Page title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }
}
