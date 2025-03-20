package org.openCart;
import org.openCart.Utils.TestListener;
import org.openCart.Utils.TestUtils;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(dataProvider = "loginData", dataProviderClass = TestUtils.class,retryAnalyzer = TestListener.class,groups = "smoke")
    public void test_login(String username, String password, Boolean expectedResult){
        TestUtils.Login(username,password);
        TestUtils.assertLoginStatus(expectedResult);
    }
}
