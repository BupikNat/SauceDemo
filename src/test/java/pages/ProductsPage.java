package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");

    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private final String REMOVE_FROM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    //Проверить что открылась страница Products (найти элемент Products по селектору)
    public String getTitle() {
        //Timeout exception проверяем нахождение локатора в коде
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    //Добавить товар в корзину
    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    //Удалить товар из корзины
    public void clickRemoveButton(String product) {
        By removeFromCart = By.xpath((String.format(REMOVE_FROM_CART_PATTERN, product)));
        driver.findElement(removeFromCart).click();
    }

    //Открыть корзину и проверить что товар в корзине
    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }
}
