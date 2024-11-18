package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //Описали 4 элемента (поля), которые находятся на странице логина 9как их найти на странице)
    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    //Конструктор родительского класса (super)
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Написали метод "open" который открывает страницу логина
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    //Написали метод логин - ввести юзернейм, затем ввести пароль и нажать на кнопку логина
    public void login(String user, String password) {
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    //Получить сообщение об ошибке
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
