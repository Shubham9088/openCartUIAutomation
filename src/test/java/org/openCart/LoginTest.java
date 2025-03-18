package org.openCart;
import org.openCart.Utils.TestUtils;
import org.openCart.Utils.openCartTestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(openCartTestListeners.class)
public class LoginTest extends BaseTest{

    @Test(dataProvider = "loginData", dataProviderClass = TestUtils.class)
    public void test_login(String username, String password, Boolean expectedResult){
        TestUtils.Login(username,password);
        TestUtils.assertLoginStatus(expectedResult);
    }
}
