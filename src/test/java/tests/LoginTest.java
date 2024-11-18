package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Page is not opened");
    }

    //Тест - пустое поле Name
    @Test
    public void checkEmptyNameDuringLogin() {

        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
    }

    //Тест - пустое поле Password
    @Test
    public void checkEmptyPasswordDuringLogin() {

        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
    }

    //Тест - введён неправильный логин (имя пользователя)
    @Test
    public void checkIncorrectUserNameDuringLogin() {

        loginPage.open();
        loginPage.login("some_wrong_username", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
    }

    //Тест - введён неправильный пароль
    @Test
    public void checkIncorrectPasswordDuringLogin() {

        loginPage.open();
        loginPage.login("user-name", "some_wrong_password");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Нет сообщения об ошибке");
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

