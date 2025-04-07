package org.openCart.Utils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public static void init(){
        if(ConfigReader.getProperty("execution_mode").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();
            //setting the os
            switch (ConfigReader.getProperty("os").toLowerCase()) {
                case "windows":
                    capabilities.setPlatform(Platform.WIN11);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
            }
            //setting the browser
            switch (ConfigReader.getProperty("browser").toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
            }
            try{
                driver.set(new RemoteWebDriver(new URL(ConfigReader.getProperty("grid_url")), capabilities));
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
        //for local execution
        }else{
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            RemoteWebDriver webDriver;

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
            driver.set(webDriver);
        }
    }

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
