package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");

    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private final String REMOVE_FROM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    //Проверить что открылась страница Products (найти элемент Products по селектору)
    @Step("Открытие страницы Products")
    public String getTitle() {

        log.info("Open Products page");
        //Timeout exception проверяем нахождение локатора в коде
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    //Добавить товар в корзину
    @Step("Добавление товара с именем {product} в корзину")
    public void clickAddButton(String product) {

        log.info("Click on Add to Cart button");
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    //Удалить товар из корзины
    @Step("Клик на кнопку удаления товара из корзины")
    public void clickRemoveButton(String product) {

        log.info("Click on Remove from Cart button");
        By removeFromCart = By.xpath((String.format(REMOVE_FROM_CART_PATTERN, product)));
        driver.findElement(removeFromCart).click();
    }

    //Открыть корзину
    @Step("Открытие корзины")
    public void clickShoppingCart() {

        log.info("Open Cart page");
        driver.findElement(CART_LINK).click();
    }
}