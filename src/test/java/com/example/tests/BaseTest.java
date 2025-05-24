package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Stealth/anti-bot settings
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Headless for CI (uncomment next line if you want to always run headless locally too)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Use a unique user-data-dir for each session (prevents conflicts in CI)
        try {
            Path tempProfile = Files.createTempDirectory("chrome-profile-");
            options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());
        } catch (Exception e) {
            // If there's an issue, just continue without the unique profile
            System.err.println("Could not create temp Chrome profile: " + e.getMessage());
        }

        // Optional: spoof user-agent (uncomment if needed)
        // options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
