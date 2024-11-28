package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.AllureUtils.takeScreenshot;

public class LoginPage extends BasePage {

    //Описали 4 элемента (поля), которые находятся на странице логина (как их найти на странице)
    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    //Конструктор родительского класса (super)
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Написали метод "open" который открывает страницу логина
    @Step("Открытие страницы логина")
    public void open() {
        driver.get("https://www.saucedemo.com/");
        takeScreenshot(driver); //скриншот, можно делать в каждом тесте, а можно через TestListener настроить чтобы скрин делался только при падении теста
    }

    //Написали метод логин - ввести юзернейм, затем ввести пароль и нажать на кнопку логина
    @Step("Вход в систему с логином {user} и паролем {password}")
    public void login(String user, String password) {
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        //takeScreenshot(driver); - скриншот прямо в тесте
    }

    //Получить сообщение об ошибке
    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
