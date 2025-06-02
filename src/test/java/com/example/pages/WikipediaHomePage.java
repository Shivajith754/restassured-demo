package com.example.pages;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Minimal POM that builds the Wikipedia search URL. */
public class WikipediaHomePage {

    /**
     * Force Wikipedia to return the normal list view instead of doing a
     * “Go-to-article” redirect when a title matches exactly.
     *
     * Docs: adding both `title=Special:Search` and `fulltext=1`
     * guarantees the Search results layout.
     */
    private static final String SEARCH_HOST =
            "https://en.wikipedia.org/w/index.php?title=Special:Search&fulltext=1&search=";

    private final WebDriver     driver;
    private final WebDriverWait wait;

    public WikipediaHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    /** Navigates directly to the HTML search-results page. */
    public WikipediaResultsPage searchFor(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String targetUrl    = SEARCH_HOST + encodedQuery;

        System.out.println("[" + query + "] Navigating to URL: " + targetUrl);
        driver.get(targetUrl);
        System.out.println("[" + query + "] Current URL after get(): " + driver.getCurrentUrl());

        return new WikipediaResultsPage(driver, wait, query);
    }
}
