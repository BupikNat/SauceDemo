package pages;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CartPage extends BasePage {

    private By productInCart = By.cssSelector(".cart_item");
    private By productPrice = By.cssSelector(".inventory_item_price");
    private String removeButton = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button[text() = 'Remove']";
    private By continueShoppingButton = By.xpath("//button[@id='continue-shopping']");
    private String nameOfAddedProduct = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_name']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение цены товара")
    public String getProductPrice(int index) {

        log.info("Getting of the product price '{}'", productPrice);
        return driver.findElements(productPrice).get(index).getText();
    }

    @Step("Удаление товара из корзины")
    public void removeProductFromCart(String itemName) {

        log.info("Click on Remove button");
        By removeButton = By.xpath(String.format(this.removeButton, itemName));
        driver.findElement(removeButton).click();
    }

    @Step("Проверка, что корзина пустая")
    public boolean isCartEmpty() {

        log.info("Check that cart is empty");
        return driver.findElements(productInCart).isEmpty();
    }

    @Step("Клик на кнопку Continue Button")
    public void clickContinueShoppingButton() {
        driver.findElement(continueShoppingButton).click();
    }

    @Step("Проверка, что товар добавлен в корзину по названию товара")
    public String getAddedProductByName(String product) {

        log.info("Check that product is added to the cart");
        By productName = By.xpath(String.format(nameOfAddedProduct, product));
        return driver.findElement(productName).getText();
    }
}
