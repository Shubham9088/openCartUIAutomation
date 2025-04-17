package org.openCart;
import org.openCart.Utils.ConfigReader;
import org.openCart.Utils.TestListener;
import org.openCart.Utils.TestUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Registration extends BaseTest{

    /**
     * Validate error message when user enter already registered email id.
     * @author shchak
     */
    @Test(retryAnalyzer = TestListener.class,groups = "smoke")
    public void testErrorMessageForAlreadyRegisteredEmail() {
        TestUtils.Register("shubham","chakole","shubhamchakole22@gmail.com","8055899488","Shubham@123","Shubham@123");
        TestUtils.verifyText(ConfigReader.getLocatorDataProperty("Register_Error_Message"));
    }

    /**
     * Validate error message when user enter different password.
     * @author shchak
     */
    @Test(retryAnalyzer = TestListener.class,groups="regression")
    public void testErrorMessageForDifferentPassword() {
        TestUtils.Register("shubham","chakole","shubhamchakole@gmail.com","8055899488","Shubham@123","Shubham");
        TestUtils.verifyText(ConfigReader.getLocatorDataProperty("Register_Password_Error_Message"));
    }
}
