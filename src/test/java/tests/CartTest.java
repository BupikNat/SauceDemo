package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Log4j2
public class CartTest extends BaseTest {

    //Добавить 1 продукт в корзину и проверить что продукт добавился по имени
    @Test (testName = "Checking by Name", description = "Check added products by name", priority = 1)
    @Description("Проверка добавления товара в корзину по имени товара")
    public void addToCartAndCheckByName() {

        log.info("Add product to the Cart and validate adding by name of the product");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        String addedProductName = cartPage.getAddedProductByName("Sauce Labs Bike Light");
        softAssert.assertEquals(addedProductName, "Sauce Labs Bike Light");
        softAssert.assertAll();

    }

    //Добавить 1 продукт в корзину и проверить что продукт добавился по цене
    @Test (testName = "Checking by Price", description = "Check added products by price", priority = 2)
    @Description("Проверка добавления товара в корзину по цене товара")
    public void addToCartAndCheckByPrice() {

        String expectedPrice = "$9.99";

        log.info("Add product to the Cart and validate adding by price of the product");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductPrice(0), expectedPrice);
        softAssert.assertAll();
    }

    //Удалить продукт из корзины и проверить что продукт удалился
    @Test (testName = "Removing 1 product", description = "Remove 1 product from the cart", priority = 3)
    @Description("Проверка удаления товара из корзины")
    public void removeItemFromCart() {

        log.info("Removing product from the Cart");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        cartPage.removeProductFromCart("Sauce Labs Bike Light");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isCartEmpty());
        softAssert.assertAll();
    }

    @Test (testName = "Adding products", description = "Add multiple products to the cart", priority = 4)
    @Description("Проверка добавления нескольких товаров в корзину")
    public void addMultipleItems() {

        log.info("Adding two products to the Cart");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        String firstAddedProduct = cartPage.getAddedProductByName("Sauce Labs Backpack");
        String secondAddedProduct = cartPage.getAddedProductByName("Sauce Labs Bike Light");
        softAssert.assertEquals(firstAddedProduct, "Sauce Labs Backpack");
        softAssert.assertEquals(secondAddedProduct, "Sauce Labs Bike Light");
        softAssert.assertAll();
    }

    @Test (testName = "Removing 2 products", description = "Remove 2 products from the cart", priority = 5)
    @Description("Проверка удаления нескольких товаров из корзины")
    public void removeMultipleItems() {

        log.info("Removing two products from the Cart");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        cartPage.removeProductFromCart("Sauce Labs Backpack");
        cartPage.removeProductFromCart("Sauce Labs Bike Light");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isCartEmpty());
        softAssert.assertAll();
    }

    //Нажать кнопку Continue shopping и вернуться на страницу продукты
    @Test (testName = "Checking Continue Shopping Button",
            description = "Check that click on Continue Sopping Button leads to Main page",
            priority = 6)
    @Description("Проверка кнопки Continue Shopping")
    public void continueShopping() {

        log.info("Click on Continue Shopping button");
        loginPage.open();
        loginPage.login(user, password);
        productsPage.getTitle();
        productsPage.clickAddButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        cartPage.clickContinueShoppingButton();
        softAssert.assertEquals(productsPage.getTitle(), "Products");
        softAssert.assertAll();
    }
}