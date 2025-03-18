package org.openCart.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void init() {
        if (ConfigReader.getProperty("browser") == null) {
            throw new RuntimeException("Browser name not provided in configuration.properties file");
        }

        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver webDriver;

        switch (browser) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "safari":
                webDriver = new SafariDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.set(webDriver);  // Assign WebDriver instance to ThreadLocal
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
