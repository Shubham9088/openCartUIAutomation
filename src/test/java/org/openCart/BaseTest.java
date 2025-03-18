package org.openCart;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openCart.Utils.ConfigReader;
import org.openCart.Utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    public static final Logger logger= LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp()throws InterruptedException{
        logger.info("Stating setUp method");
        //creating the driver object
        DriverManager.init();
        //launch the url
        DriverManager.getDriver().get(ConfigReader.getProperty("URL"));
        logger.info("setUp method completed -- browser opened");
        DriverManager.getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.getDriver().quit();
        logger.info("Browser closed");
    }
}
