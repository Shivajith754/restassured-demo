//  ── src/test/java/com/example/bdd/steps/WikipediaSearchSteps.java
package com.example.bdd.steps;

import com.example.pages.WikipediaHomePage;
import com.example.pages.WikipediaResultsPage;
import com.example.tests.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class WikipediaSearchSteps extends BaseTest {

    private WikipediaResultsPage resultsPage;
    private String               query;

    @Given("I am on the Wikipedia home page")
    public void i_am_on_home() {
        driver().get("https://en.wikipedia.org");
    }

    @When("I search for {string}")
    public void i_search_for(String q) {
        query = q;
        resultsPage = new WikipediaHomePage(driver(), getWait()).searchFor(q);
    }

    @Then("at least one result is shown")
    public void at_least_one_result() {
        Assert.assertTrue(resultsPage.hasAtLeastOneResult(),
                "Expected ≥1 result for " + query);
    }

    @Then("the page title contains {string}")
    public void title_contains(String expected) {
        Assert.assertTrue(resultsPage.titleContainsQuery(),
                "Title should contain " + expected);
    }
}
