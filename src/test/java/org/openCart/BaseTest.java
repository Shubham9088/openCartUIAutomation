package org.openCart;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openCart.Utils.ConfigReader;
import org.openCart.Utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        logger.info("Starting setUp method");
        DriverManager.init();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigReader.getProperty("URL"));
        logger.info("setUp method completed -- browser opened");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Browser closed");
    }
}
