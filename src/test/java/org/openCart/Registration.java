package org.openCart;
import org.openCart.Utils.TestUtils;
import org.openCart.Locator.Locators;
import org.openCart.Utils.openCartTestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(openCartTestListeners.class)
public class Registration extends BaseTest{

    @Test
    public void test_register() {
        TestUtils.Register("test","test","test@gmail.com","Test@123");
        TestUtils.verifyText(Locators.Register_Success_Message);
    }
}
