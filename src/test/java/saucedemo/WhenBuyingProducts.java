package saucedemo;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import saucedemo.pages.CartPage;
import saucedemo.pages.CheckoutPage;
import saucedemo.pages.LoginPage;
import saucedemo.pages.ProductsPage;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenBuyingProducts {
    String firstName = "Francisco";
    String lastName = "Pantoja";
    String postalCode = "14009";
    Integer nItems = 4;
    Double cartTotal = 111.96;
    Double checkoutTotal = 120.92;
    Double taxTotal = 8.96;
    String successMessage = "Thank you for your order!";

    @Managed(driver = "chrome", options = "headless")
    WebDriver driver;

    @Steps
    NavigateActions navigate;

    @Steps
    LoginPage loginPage;

    @Steps
    ProductsPage productsPage;

    @Steps
    CartPage cartPage;

    @Steps
    CheckoutPage checkoutPage;

    @Test
    void aSuccessMessageShouldAppearAfterBuying() {
        navigate.toTheSauceDemoLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        navigate.toAboutPage();
        navigate.goBackOnePage();
        productsPage.sortProductsByPriceFromLowestToHighest();
        productsPage.addTheNMostExpensiveProductsToCart(nItems);
        productsPage.verifyCartHasNItems(nItems);

        cartPage.goToCartAndVerifyTotal(cartTotal);
        cartPage.checkout();
        checkoutPage.fillPersonalDataAndContinue(firstName, lastName, postalCode);


        checkoutPage.verifyTotals(cartTotal, checkoutTotal, taxTotal);
        checkoutPage.showCheckoutInfo();
        checkoutPage.finishBuyAndVerifySuccess(successMessage);
    }
}
