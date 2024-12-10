package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    //enabled = false - тест скипается, retryAnalyzer - перезапустить тест (для тестов которые падают периодически например из-за таймаута)
    @Test(testName = "Позитивный логин", description = "Проверить позитивный логин",
            priority = 1, enabled = true)  //retryAnalyzer = Retry.class)
    @Epic("Страница логина")    //Название эпика
    @Feature("Проверка логина в приложение")   //Функционал, который проверяется
    @Story("Валидный логин")   //Название стори
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @Owner("Natalia Boytsova")
    @Issue("Bug-10")   //Номер дефекта
    @TmsLink("TC-10") //Ссылка на тест кейс (в тестрейле/зефире - любой тест менеджмент системе)
    @Description("Проверка валидного логина")
    @Flaky   //Нестабильные тесты
    public void checkLogin() {

        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Page is not opened");
    }

    //Тест - пустое поле Name
    @Test(testName = "Пустое имя", description = "Оставить Поле имя пустым",
            priority = 2, enabled = true)
    @Description("Пустое поле Name при логине")
    public void checkEmptyNameDuringLogin() {

        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Нет сообщения об ошибке");
    }

    //Тест - пустое поле Password
    @Test(testName = "Пустой пароль", description = "Оставить поле Пароль пустым",
            priority = 3, enabled = true)
    @Description("Пустое поле Password при логине")
    public void checkEmptyPasswordDuringLogin() {

        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Нет сообщения об ошибке");
    }

    //Тест - введён неправильный логин (имя пользователя)
    @Test(testName = "Неправильное имя", description = "Ввести неправильное имя юзера",
            priority = 4, enabled = true)
    @Description("Ввести невалидное имя пользователя")
    public void checkIncorrectUserNameDuringLogin() {

        loginPage.open();
        loginPage.login("some_wrong_username", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
    }

    //Тест - введён неправильный пароль
    @Test(testName = "Неправильный пароль", description = "Ввести неправильный пароль",
            priority = 5, enabled = true)
    @Description("Ввести невалидный пароль")
    public void checkIncorrectPasswordDuringLogin() {

        loginPage.open();
        loginPage.login("user-name", "some_wrong_password");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"user-name", "some_wrong_password", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(dataProvider = "LoginData", testName = "Цикл тестов на логин")
    @Description("Цикл тестов LoginData с разными данными логин/пароль")
    public void test(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getErrorMessage(),
                expectedError,
                "Неправильное сообщение об ошибке");
    }



    /*Метод - скопировать в буфер обмена
    public void copyMethod() {

        String copyIntoBuffer = "TeachMeSkills";

        StringSelection stringSelection = new StringSelection(copyIntoBuffer);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        //Достать из буфера обмена

        System.out.println(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    */
}