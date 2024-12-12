package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.time.Duration;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod
    @Description("Открытие браузера")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        //Настройка и инициация драйвера, можно запустить 3 разных браузера
        log.info("Open browser");
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);   //инициализация
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else if(browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }

        //context.setAttribute("driver", driver); - работает только с версией TestNG 7.4.0, если выше версия то скрины при падении теста делать не будет
        TestListener.setDriver(driver);
        loginPage = new LoginPage(driver);   //объявление (инициализация) переменной loginPage класса LoginPage и передача в неё переменной driver
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    //Закрыть браузер
    @AfterMethod(alwaysRun = true)
    @Description("Закрытие браузера")
    public void tearDown(ITestResult result) {
        log.info("Close browser");
//        if (ITestResult.FAILURE == result.getStatus()) {
//            AllureUtils.takeScreenshot(driver);
//        }
        if (driver != null) {
            driver.quit();
        }
    }
}