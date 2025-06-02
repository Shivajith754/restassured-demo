package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/** POM for Wikipedia search results. */
public class WikipediaResultsPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;
    private final String        query;

    /** Each hit contains an <a> below .mw-search-result-heading. */
    private static final By FIRST_RESULT_HEADING =
            By.cssSelector(".mw-search-result-heading > a");

    private static final By MAIN_CONTENT_AREA =
            By.id("mw-content-text");

    public WikipediaResultsPage(WebDriver driver,
                                WebDriverWait wait,
                                String query) {
        this.driver = driver;
        this.wait   = wait;
        this.query  = query;

        System.out.println("[" + query + "] WikipediaResultsPage ctor – waiting for main content.");
        try {
            // Wait for the main container *and* at least one result link
            this.wait.until(ExpectedConditions.presenceOfElementLocated(MAIN_CONTENT_AREA));
            this.wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_RESULT_HEADING));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("[" + query + "] Timeout loading search results at: "
                    + driver.getCurrentUrl());
            throw e;
        }
    }

    /* ------------ helper assertions ------------ */

    public boolean titleContainsQuery() {
        String currentTitle = driver.getTitle().toLowerCase();
        return currentTitle.contains(query.toLowerCase());
    }

    public boolean hasAtLeastOneResult() {
        List<WebElement> results = driver.findElements(FIRST_RESULT_HEADING);
        return !results.isEmpty();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
