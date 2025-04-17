package org.openCart.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener, IRetryAnalyzer {

    private final int maxRetryCount = 1;
    private static ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initializes the ExtentReports and configures the ExtentSparkReporter
     * @param content the ITestContext provided by TestNG at the start of the test suite
     * @author shchak
     */
    public void onStart(ITestContext content) {
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Open Cart Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    /**
     * Logs the successful execution of a test case in the Extent Report.
     * Creates a test entry with the test method name and assigns any associated groups.
     * @param result the ITestResult object containing information about the executed test method.
     * @author shchak
     */
    public void onTestSuccess(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.PASS, "Test passed");
    }


    /**
     * Logs the failed execution of a test case in the Extent Report.
     * Creates a test entry with the test method name and assigns any associated groups.
     * Attach a screenshot to the extent report.
     * @param result the ITestResult object containing information about the executed test method.
     * @author shchak
     */
    public void onTestFailure(ITestResult result) {
        logger.info("onTestFailure method executing");
        test.set(extent.createTest(result.getMethod().getMethodName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.FAIL, result.getThrowable().getMessage());
        //capturing screenshot for failed tests
        try {
            if (DriverManager.getDriver() != null) {
                TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
                File source = ts.getScreenshotAs(OutputType.FILE);
                File destination = new File(System.getProperty("user.dir") + "/screenshot/" + result.getName() + ".png");
                FileUtils.copyFile(source, destination);
                //Adding screenshot to extent report
                test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/screenshot/" + result.getName() + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Logs the skipped execution of a test case in the Extent Report.
     * Creates a test entry with the test method name and assigns any associated groups.
     * @param result the ITestResult object containing information about the executed test method.
     * @author shchak
     */
    public void onTestSkipped(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.SKIP, "Test skipped");
    }

    /**
     * Finalizes the Extent Report after all tests have been executed.
     * Flushes the report data and opens the generated HTML report in the default browser.
     * @param content the ITestContext provided by TestNG at the end of the test suite
     * @author shchak
     */
    public void onFinish(ITestContext content) {
        extent.flush();
        //Opening extent report
        File extentReport = new File(System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html");
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test case will be retried 1 times if it gets failed
     * @author shchak
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount.get() < maxRetryCount) {
            retryCount.set(retryCount.get() + 1);
            return true;
        }
        return false;
    }
}

