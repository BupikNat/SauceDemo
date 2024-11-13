import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin () {

        driver.get("https://www.saucedemo.com");
        WebElement userNameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement logoOnLoginPage = driver.findElement(By.className("login_logo"));
        WebElement bodyOnLoginPage = driver.findElement(By.tagName("body"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Тест на логин - юзер залогинился и перешёл на страницу Products
        String title = driver.findElement((By.cssSelector(".title"))).getText();
        assertEquals(title, "Products", "Page is not opened");
    }

    //Тест - пустое поле Password
    @Test
    public void checkEmptyPasswordDuringLoging(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(title,"Epic sadface: Password is required");
    }

    //Тест - введён неправильный логин (имя пользователя)
    @Test
    public void checkIncorrectUserNameDuringLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(title,
                "Epic sadface: Username and password do not match any user in this service");
    }

    //Тест - введён неправильный пароль
    @Test
    public void checkIncorrectPasswordDuringLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("qqqq");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(title,
                "Epic sadface: Username and password do not match any user in this service");
    }
}
