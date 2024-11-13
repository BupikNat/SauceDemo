import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void locatorTest() {

        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name"));
        //Найти элементы
        driver.findElement(By.id("password"));
        driver.findElement(By.className("login_credentials"));
        driver.findElement(By.tagName("form"));
        //Найти элементы + ввести значение элементов + нажать на кнопку логина
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Bike"));

        //X-Path
        driver.findElement(By.xpath("//div/a[@id='item_4_title_link']"));  //атрибут
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));  //текст
        driver.findElement(By.xpath("//div[contains(@id,'cart_container')]"));  //частичное совпадение атрибута
        driver.findElement(By.xpath("//div[contains(text(), 'Swag')]"));  //частичное совпадение текста
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']//ancestor::div[1]"));  //ancestor
        driver.findElement(By.xpath("descendant::div[@class='pricebar'][1]"));  //descendant
        driver.findElement(By.xpath("//div[@class='pricebar']/following::div"));  //following
        driver.findElement(By.xpath("//footer//parent::li/a[text()='Facebook']"));  //footer
        driver.findElement(By.xpath("//li[@class='social_linkedin']//preceding::li[@class='social_facebook']"));   //precending
        driver.findElement(By.xpath("//a[@target='_blank' and text()='Twitter']"));   //AND

        //CSS-Selectors
        driver.findElement(By.cssSelector(".page_wrapper"));   //class
        driver.findElement(By.cssSelector(".bm-menu-wrap .bm-menu"));   //class1.class2
        driver.findElement(By.cssSelector(".login_wrapper .login_wrapper-inner"));  //class1. class2
        driver.findElement(By.cssSelector("#user-name"));   //#id
        driver.findElement(By.cssSelector("footer"));  //tagname
        driver.findElement(By.cssSelector("footer.footer"));   //tagname.class
        driver.findElement(By.cssSelector("[aria-hidden=true]"));   //[atrubute=value]
        driver.findElement(By.cssSelector("[class~='menu-item']"));   //[atrubute~=value]
        driver.findElement(By.cssSelector("[data-test|='header']"));   //[atrubute|=value]
        driver.findElement(By.cssSelector("[class ^= 'footer']"));   //[atrubute^=value]
        driver.findElement(By.cssSelector("[name$='backpack']"));   //[atrubute$=value]
        driver.findElement(By.cssSelector("[id*='-menu-btn']"));   //[atrubute*=value]

    }
}
