package org.openCart;
import org.openCart.Utils.TestListener;
import org.openCart.Utils.TestUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{

    @Test(dataProvider = "loginData", dataProviderClass = TestUtils.class,groups = "smoke")
    public void test_login(String username, String password, Boolean expectedResult){
        TestUtils.Login(username,password);
        TestUtils.assertLoginStatus(expectedResult);
    }
}
