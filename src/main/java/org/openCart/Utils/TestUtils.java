package org.openCart.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.Duration;


public class TestUtils {

    private static Logger utilslLogger = LogManager.getLogger(TestUtils.class);

    /**
     * Generates a dynamic XPath locator by replacing placeholders in the locator string with actual values.
     * Placeholders in the locator string should be in the format {0}, {1}, etc.
     * @param locatorName the XPath template string containing placeholders
     * @param string the values to replace the placeholders with
     * @return the generated XPath locator
     * @author shchak
     */
    public static By getElementXpath(String locatorName, String... string) {
        String locator=locatorName;
        for (int i = 0; i < string.length; i++) {
            locator = locator.replace("{" + i + "}", string[i]);
        }
        return By.xpath(locator);
    }

    /**
     * Returns a WebDriverWait object configured with a timeout of 30 seconds.
     * @return WebDriverWait object
     * @author shchak
     */
    public static WebDriverWait waitForElement() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
    }

    /**
     * Returns a JavascriptExecutor object.
     * @return JavascriptExecutor object
     * @author shchak
     */
    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js;
    }

    /**
     * Launches the login page by clicking on the "Account" dropdown and then clicking on the "Login" button.
     * @author shchak
     */
    public static void launchLoginPage() {
        WebElement dropdown = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByClassAndText"),ConfigReader.getLocatorDataProperty("Account_Dropdown"))));
        try {
            dropdown.click();
            utilslLogger.info("Dropdown clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click dropdown " + ConfigReader.getLocatorDataProperty("Account_Dropdown") + "\nError" + e.getMessage());
        }

        WebElement login = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByText"),ConfigReader.getLocatorDataProperty("Account_Login"))));
        try {
            login.click();
            utilslLogger.info("Login button clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click login " + ConfigReader.getLocatorDataProperty("Account_Login") + "\nError" + e.getMessage());
        }
    }

    /**
     * Launching the registration page by clicking on the "Account" dropdown and then clicking on the "Register" button.
     * @author shchak
     */
    public static void launchRegistrationPage() {
        WebElement dropdown = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByClassAndText"),ConfigReader.getLocatorDataProperty("Account_Dropdown"))));
        try {
            dropdown.click();
            utilslLogger.info("Dropdown clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click dropdown " + ConfigReader.getLocatorDataProperty("Account_Dropdown") + "\nError" + e.getMessage());
        }

        WebElement register = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByText"),ConfigReader.getLocatorDataProperty("Register"))));
        try {
            register.click();
            utilslLogger.info("Register button clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click register " + ConfigReader.getLocatorDataProperty("Register") + "\nError" + e.getMessage());
        }
    }

    /**
     * Logs in with the provided username and password.
     * @param username the username to log in with
     * @param password the password to log in with
     * @author shchak
     */
    public static void Login(String username, String password) {
        launchLoginPage();
        utilslLogger.info("Login page launched");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Login_Username_Placeholder"), username);
        utilslLogger.info("Username " + username + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Login_Password_Placeholder"), password);
        utilslLogger.info("Password " + password + " entered");
        clickButton(ConfigReader.getLocatorDataProperty("Button_Type_Submit"), ConfigReader.getLocatorDataProperty("Login_Button"));
        utilslLogger.info("Login button clicked");
    }

    /**
     * Registers a new user with the provided details.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email address of the user
     * @param mobile the mobile number of the user
     * @param password the password for the user
     * @param confirmPassword the confirmation password for the user
     * @author shchak
     */
    public static void Register(String firstName, String lastName, String email, String mobile, String password, String confirmPassword) {
        launchRegistrationPage();
        utilslLogger.info("Registration page launched");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_FirstName_Placeholder"), firstName);
        utilslLogger.info("First name " + firstName + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_LastName_Placeholder"), lastName);
        utilslLogger.info("Last name " + lastName + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_Email_Placeholder"), email);
        utilslLogger.info("Email " + email + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_Telephone_Placeholder"), mobile);
        utilslLogger.info("Telephone" + mobile + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_Password_Placeholder"), password);
        utilslLogger.info("Password " + password + " entered");
        enterTextInInputField(ConfigReader.getLocatorDataProperty("Register_ConfirmPassword_Placeholder"), confirmPassword);
        WebElement agreeBtn = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByName"),ConfigReader.getLocatorDataProperty("Agree_Input"))));
        try {
            getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", agreeBtn);
            Thread.sleep(1000);
            agreeBtn.click();
            utilslLogger.info("Agree button clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click agree " + ConfigReader.getLocatorDataProperty("Agree_Input") + "\nError" + e.getMessage());
        }
        clickButton(ConfigReader.getLocatorDataProperty("Button_Type_Submit"), ConfigReader.getLocatorDataProperty("Register_Button_Value"));
        utilslLogger.info("Register button clicked");
    }

    /**
     * Click button by type and value
     * @param type type attribute of button
     * @param value value attribute of button
     */
    public static void clickButton(String type, String value) {
        WebElement button = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByTypeAndValue"), type, value)));
        try {
            button.click();
            utilslLogger.info("Button " + value + " clicked");
        } catch (Exception e) {
            utilslLogger.error("Error in click button " + value + "\nError" + e.getMessage());
        }
    }


    /**
     * Enter text in the input field
     * @param placeholder placeholder attribute of input field
     * @param text text to enter in input field
     * @author shchak
     */
    public static void enterTextInInputField(String placeholder, String text) {
        WebElement inputBox = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByPlaceholder"), placeholder)));
        try {
            inputBox.sendKeys(text);
            utilslLogger.info("Text " + text + " entered");
        } catch (Exception e) {
            utilslLogger.error("Error in enter text " + text + "\nError" + e.getMessage());
        }
    }

    /**
     * Assert login status
     * @param status status of login true or false
     * @author shchak
     */
    public static void assertLoginStatus(boolean status) {
        if (status) {
            WebElement editAccount = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByText"),ConfigReader.getLocatorDataProperty("EditAccount"))));
            Assert.assertTrue(editAccount.isDisplayed());
        } else {
            verifyText(ConfigReader.getLocatorDataProperty("Login_Error_Message"));
        }
    }

    /**
     * Login data provider
     * Reads data from excel file and return data in 2D object array
     * @return 2D object array
     * @throws IOException throws IOException if xlsx file is not found.
     * @author shchak
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        ExcelUtils excel = new ExcelUtils(System.getProperty("user.dir") + "/src/main/java/org/openCart/TestData/LoginTestData.xlsx");
        int rowsCount = excel.getRowCount("Login");
        int columnsCount = excel.getCellCount("Login", 1);
        Object[][] data = new Object[rowsCount][columnsCount];
        for (int i = 1; i <= rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (excel.getCellData("Login", i, j).equalsIgnoreCase("true") || excel.getCellData("Login", i, j).equalsIgnoreCase("false")) {
                    //parsing the string "true" or "false" to boolean
                    data[i - 1][j] = Boolean.valueOf(excel.getCellData("Login", i, j));
                } else {
                    data[i - 1][j] = excel.getCellData("Login", i, j);
                }
            }
        }
        return data;
    }

    /**
     * Verify test message is displayed in the UI
     * @param text text to verify in UI
     * @author shchak
     */
    public static void verifyText(String text) {
        System.out.println(text);
        try {
            boolean isTextPresent = waitForElement().until(ExpectedConditions.elementToBeClickable(getElementXpath(ConfigReader.getLocatorProperty("objByText"), text))).isDisplayed();
            Assert.assertTrue(isTextPresent);
            utilslLogger.info("Text " + text + " is found");
        } catch (TimeoutException e) {
            utilslLogger.error("Text " + text + "is not found" + "\nError" + e.getMessage());
            Assert.fail();
        }
    }
}
