package com.example.bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        features = "src/test/resources/features",
        // 👇 either of these works
        // glue = "com.example",                        // broadest
        glue = { "com.example.bdd.steps", "com.example.bdd.hooks" },
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests { }
