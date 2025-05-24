package com.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.tests.BaseTest; // import your base

public class DuckDuckGoUiTest extends BaseTest {

    // DataProvider supplies search terms
    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][] {
                {"Selenium WebDriver"},
                {"TestNG tutorial"},
                {"Java automation"},
                {"OpenAI ChatGPT"}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testDuckDuckGoSearch(String query) {
        driver.get("https://duckduckgo.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();

        // Wait for the page title to contain the search term
        wait.until(ExpectedConditions.titleContains(query));
        System.out.println("Page title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains(query));
    }
}
