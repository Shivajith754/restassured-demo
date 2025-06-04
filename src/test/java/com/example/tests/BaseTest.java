package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;

public class BaseTest {

    /** one WebDriver per thread (safe for TestNG & Cucumber parallelism) */
    private static final ThreadLocal<WebDriver>     TL_DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> TL_WAIT   = new ThreadLocal<>();

    /* ── Bootstrap / teardown shared by TestNG *and* Cucumber ── */
    protected void startDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions opts = new ChromeOptions();
        opts.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        opts.setExperimentalOption("useAutomationExtension", false);
        opts.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--headless=new", "--no-sandbox", "--disable-dev-shm-usage"
        );

        try {
            Path tmp = Files.createTempDirectory("chrome-profile-");
            opts.addArguments("--user-data-dir=" + tmp.toAbsolutePath());
        } catch (Exception ignored) { }

        WebDriver d = new ChromeDriver(opts);
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        TL_DRIVER.set(d);
        TL_WAIT.set(new WebDriverWait(d, Duration.ofSeconds(10)));
    }

    protected void stopDriver() {
        WebDriver d = TL_DRIVER.get();
        if (d != null) d.quit();
        TL_DRIVER.remove();
        TL_WAIT.remove();
    }

    /* ── Getters used by all tests ── */
    protected WebDriver      driver() { return TL_DRIVER.get(); }
    protected WebDriverWait  getWait() { return TL_WAIT.get(); }

    /* ── Original TestNG hooks stay intact ── */
    @BeforeMethod public void setUp()   { startDriver(); }
    @AfterMethod  public void tearDown(){ stopDriver();  }
}
