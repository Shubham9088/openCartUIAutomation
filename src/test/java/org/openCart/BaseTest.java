package org.openCart;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openCart.Utils.ConfigReader;
import org.openCart.Utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * Initializing the WebDriver
     * Maximizing the window
     * launching the URL
     * @author shchak
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        logger.info("Starting setUp method");
        DriverManager.init();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigReader.getTestDataProperty("URL"));
        logger.info("setUp method completed -- browser opened");
    }

    /**
     * Quiting the driver
     * @author shchak
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("TearDown method executing");
        DriverManager.quitDriver();
        logger.info("Browser closed");
    }
}
