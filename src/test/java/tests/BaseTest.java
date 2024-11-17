package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        //Настройка и инициация драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);   //инициализация
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);   //объявление (инициализация) переменной loginPage класса LoginPage и передача в неё переменной driver
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    //Закрыть браузер
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
