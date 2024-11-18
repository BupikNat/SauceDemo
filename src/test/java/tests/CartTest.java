package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    //Добавить 1 продукт в корзину и проверить что продукт добавился по имени
    @Test
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
    @Test
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
    @Test
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

    @Test
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

    @Test
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
    @Test
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