package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

import static utils.AllureUtils.takeScreenshot;


@Log4j2
public class TestListener implements ITestListener {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        TestListener.driver = driver;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //System.out.printf("======================================== STARTING TEST %s ========================================%n", iTestResult.getName());
        log.info(String.format("======================================== STARTING TEST %s ========================================%n", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
        //        getExecutionTime(iTestResult));
        log.info(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================%n"), iTestResult.getName(),
                getExecutionTime(iTestResult));
        //WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        //takeScreenshot(driver);  //добавляем скриншот в этот метод (Failure), чтобы скрин если тест удачный
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
        //        getExecutionTime(iTestResult));
        log.info(String.format("======================================== FAILED TEST %s Duration: %ss ========================================%n"), iTestResult.getName(),
                getExecutionTime(iTestResult));
        //WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        takeScreenshot(driver);  //добавляем скриншот в этот метод (Failure), чтобы скрин делался именно при падении теста
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //System.out.printf("======================================== SKIPPING TEST %s ========================================%n", iTestResult.getName());
        log.info(String.format("======================================== SKIPPING TEST %s ========================================%n"), iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
