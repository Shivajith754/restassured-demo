//  ── src/test/java/com/example/bdd/hooks/DriverHooks.java
package com.example.bdd.hooks;

import com.example.tests.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks extends BaseTest {

    @Before     // runs before every Scenario
    public void openBrowser()  { startDriver(); }

    @After      // runs after every Scenario
    public void closeBrowser() { stopDriver(); }
}
