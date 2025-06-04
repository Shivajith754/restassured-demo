package com.example.uitests;

import com.example.pages.WikipediaHomePage;
import com.example.pages.WikipediaResultsPage;
import com.example.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/** Smoke test verifying that Wikipedia returns results. */
public class WikipediaUiTest extends BaseTest {

    @DataProvider
    public Object[][] queries() {
        return new Object[][]{
                {"Selenium WebDriver"},
                {"TestNG"},
                {"Java (programming language)"},
                {"OpenAI"}
        };
    }

    @Test(dataProvider = "queries")
    public void searchShouldWork(String query) {

        // 🔻  changed line: use getters, not fields
        WikipediaResultsPage results =
                new WikipediaHomePage(driver(), getWait()).searchFor(query);

        Assert.assertTrue(results.titleContainsQuery(),
                "Title should contain '" + query + "', but was: "
                        + results.getPageTitle());

        Assert.assertTrue(results.hasAtLeastOneResult(),
                "Expected at least one result for '" + query + "'.");
    }
}
