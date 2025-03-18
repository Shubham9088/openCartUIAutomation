package org.openCart.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private static WebDriver driver;

    public static void init(){
        if(ConfigReader.getProperty("browser")==null){
            throw new RuntimeException("Browser name not provided in configuration.properties file");
        }
        String browser = ConfigReader.getProperty("browser");
        browser= browser.toLowerCase();
        switch (browser){
            case "chrome":
                driver= new ChromeDriver();
                break;
            case "edge":
                driver=new EdgeDriver();
                break;
            case "safari":
                driver=new SafariDriver();
                break;
            case "firefox":
                driver=new FirefoxDriver();
                break;
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
