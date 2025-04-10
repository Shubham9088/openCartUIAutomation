package org.openCart.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openCart.Locator.LocatorUtils;
import org.openCart.Locator.Locators;
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

    private static Logger utilslLogger=LogManager.getLogger(TestUtils.class);

    //Explicit wait
    public static WebDriverWait waitForElement(){
        return new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(30));
    }

    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js=(JavascriptExecutor) DriverManager.getDriver();
        return js;
    }

    public static void launchLoginPage(){
        WebElement dropdown=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getDropdownLocator(Locators.Account_Dropdown)));
        try{
            dropdown.click();
            utilslLogger.info("Dropdown clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click dropdown " +Locators.Account_Dropdown+ "\nError" + e.getMessage());
        }

        WebElement login=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocatorByText(Locators.Account_Login)));
        try{
            login.click();
            utilslLogger.info("Login button clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click login " +Locators.Account_Login+ "\nError" + e.getMessage());
        }
    }

    public static void launchRegistrationPage(){
        WebElement dropdown=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getDropdownLocator(Locators.Account_Dropdown)));
        try{
            dropdown.click();
            utilslLogger.info("Dropdown clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click dropdown " +Locators.Account_Dropdown+ "\nError" + e.getMessage());
        }

        WebElement register=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocatorByText(Locators.Register)));
        try{
            register.click();
            utilslLogger.info("Register button clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click register " +Locators.Register+ "\nError" + e.getMessage());
        }
    }

    public static void Login(String username,String password){
        launchLoginPage();
        utilslLogger.info("Login page launched");
        enterTextInInputField(Locators.Login_Username_Placeholder,username);
        utilslLogger.info("Username "+username+" entered");
        enterTextInInputField(Locators.Login_Password_Placeholder,password);
        utilslLogger.info("Password "+password+" entered");
        clickButton(Locators.Button_Type_Submit,Locators.Login_Button);
        utilslLogger.info("Login button clicked");
    }

    public static void Register(String firstName,String lastName,String email,String mobile,String password, String confirmPassword){
        launchRegistrationPage();
        utilslLogger.info("Registration page launched");
        enterTextInInputField(Locators.Register_FirstName_Placeholder,firstName);
        utilslLogger.info("First name "+firstName+" entered");
        enterTextInInputField(Locators.Register_LastName_Placeholder,lastName);
        utilslLogger.info("Last name "+lastName+" entered");
        enterTextInInputField(Locators.Register_Email_Placeholder,email);
        utilslLogger.info("Email "+email+" entered");
        enterTextInInputField(Locators.Register_Telephone_Placeholder,mobile);
        utilslLogger.info("Telephone" +mobile+" entered");
        enterTextInInputField(Locators.Register_Password_Placeholder,password);
        utilslLogger.info("Password "+password+" entered");
        enterTextInInputField(Locators.Register_ConfirmPassword_Placeholder,confirmPassword);
        WebElement agreeBtn=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocatorByName(Locators.Agree_Input)));
        try{
            getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", agreeBtn);
            Thread.sleep(1000);
            agreeBtn.click();
            utilslLogger.info("Agree button clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click agree " +Locators.Agree_Input+ "\nError" + e.getMessage());
        }
        clickButton(Locators.Button_Type_Submit,Locators.Register_Button_Value);
        utilslLogger.info("Register button clicked");
    }

    public static void clickButton(String type, String value){
        WebElement button=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getButtonLocator(type, value)));
        try{
            button.click();
            utilslLogger.info("Button "+value+" clicked");
        }catch (Exception e){
            utilslLogger.error("Error in click button " +value+ "\nError" + e.getMessage());
        }
    }


    public static void enterTextInInputField(String placeholder, String text){
        WebElement inputBox=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocatorByPlaceholder(placeholder)));
        try{
            inputBox.sendKeys(text);
            utilslLogger.info("Text "+text+" entered");
        }catch (Exception e){
            utilslLogger.error("Error in enter text " +text+ "\nError" + e.getMessage());
        }
    }

    public static void assertLoginStatus(boolean status){
        if(status){
            WebElement editAccount=waitForElement().until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocatorByText(Locators.Edit_Account)));
            Assert.assertTrue(editAccount.isDisplayed());
        }else {
            verifyText(Locators.Login_Error_Message);
        }
    }

    @DataProvider(name="loginData")
    public Object[][] loginData() throws IOException {
        ExcelUtils excel = new ExcelUtils(System.getProperty("user.dir")+"/src/main/java/org/openCart/TestData/LoginTestData.xlsx");
        int rowsCount = excel.getRowCount("Login");
        int columnsCount = excel.getCellCount("Login", 1);
        Object[][] data = new Object[rowsCount][columnsCount];
        for (int i = 1; i <= rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if(excel.getCellData("Login", i, j).equalsIgnoreCase("true") || excel.getCellData("Login", i, j).equalsIgnoreCase("false")){
                    //parsing the string "true" or "false" to boolean
                    data[i - 1][j] = Boolean.valueOf(excel.getCellData("Login", i, j));
                }else{
                    data[i - 1][j] = excel.getCellData("Login", i, j);
                }
            }
        }
        return data;
    }

    /**
     * Verify test message is displayed in the UI
     * @param text
     */
    public static void verifyText(String text){
        try{
            boolean isTextPresent=waitForElement().until(ExpectedConditions.textToBePresentInElementLocated(LocatorUtils.getLocatorByText(text),text));
            Assert.assertTrue(isTextPresent);
            utilslLogger.info("Text "+text+" is found");
        } catch (TimeoutException e) {
            utilslLogger.error("Text " +text+ "is not found"+"\nError" + e.getMessage());
            Assert.fail();
        }
    }
}
