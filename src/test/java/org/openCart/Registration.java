package org.openCart;
import org.openCart.Utils.TestUtils;
import org.openCart.Locator.Locators;
import org.openCart.Utils.openCartTestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(openCartTestListeners.class)
public class Registration extends BaseTest{

    /**
     * Validate error message when user enter already registered email id
     * @author shchak
     */
    @Test
    public void testErrorMessageForAlreadyRegisteredEmail() {
        TestUtils.Register("shubham","chakole","shubhamchakole22@gmail.com","8055899488","Shubham@123","Shubham@123");
        TestUtils.verifyText(Locators.Register_Error_Message);
    }

    @Test
    public void testErrorMessageForDifferentPassword() {
        TestUtils.Register("shubham","chakole","shubhamchakole@gmail.com","8055899488","Shubham@123","Shubham");
        TestUtils.verifyText(Locators.Register_Password_Error_Message);
    }

}
