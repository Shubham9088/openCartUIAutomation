package org.openCart.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class openCartTestListeners implements ITestListener, IRetryAnalyzer {


    private final int maxRetryCount = 2;
    private int retry = 0;

    /**
     * Test case will be retried 2 times if it gets failed
     */
    @Override
    public boolean retry(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && retry < maxRetryCount) {
            retry++;
            return true;
        }
        return false;
    }

    /**
     * Screenshot will be captured if test case gets failed
     *
     * @param result
     */
    public void onTestFailure(ITestResult result) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/screenshot/" + result.getName() + ".png");
            source.renameTo(destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
