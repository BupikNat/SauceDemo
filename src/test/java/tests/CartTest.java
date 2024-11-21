package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    //Добавить 1 продукт в корзину и проверить что продукт добавился по имени
    @Test (testName = "Checking by Name", description = "Check added products by name", priority = 1)
    public void addToCartAndCheckByName() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
    public void addToCartAndCheckByPrice() {

        String expectedPrice = "$9.99";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductPrice(0), expectedPrice);
        softAssert.assertAll();
    }

    //Удалить продукт из корзины и проверить что продукт удалился
    @Test (testName = "Removing 1 product", description = "Remove 1 product from the cart", priority = 3)
    public void removeItemFromCart() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.getTitle();
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        cartPage.removeProductFromCart("Sauce Labs Bike Light");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isCartEmpty());
        softAssert.assertAll();
    }

    @Test (testName = "Adding products", description = "Add multiple products to the cart", priority = 4)
    public void addMultipleItems() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
    public void removeMultipleItems() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
    @Test (testName = "Checking Continue Shopping Button", description = "Check that click on Continue Sopping Button leads to Main page", priority = 6)
    public void continueShopping() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.getTitle();
        productsPage.clickAddButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickShoppingCart();

        SoftAssert softAssert = new SoftAssert();
        cartPage.clickContinueShoppingButton();
        softAssert.assertEquals(productsPage.getTitle(), "Products");
        softAssert.assertAll();
    }
}