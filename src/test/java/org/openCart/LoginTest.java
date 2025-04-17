package org.openCart;
import org.openCart.Utils.TestListener;
import org.openCart.Utils.TestUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{

    /**
     * Login test using data-driven testing approach.
     * Validates the login functionality with different sets of credentials and expected outcomes.
     * @param username the username of user.
     * @param password the password of the user.
     * @param expectedResult the expected login result (true for success, false for failure).
     */
    @Test(dataProvider = "loginData", dataProviderClass = TestUtils.class,groups = "smoke")
    public void test_login(String username, String password, Boolean expectedResult){
        TestUtils.Login(username,password);
        TestUtils.assertLoginStatus(expectedResult);
    }
}
