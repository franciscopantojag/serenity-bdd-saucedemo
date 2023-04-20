package saucedemo.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.support.FindBy;
import saucedemo.NavigateActions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {
    ProductsPage productsPage;

    @FindBy(id = "checkout")
    WebElementFacade checkoutButton;

    @Step
    public void goToCartAndVerifyTotal(Double cartTotal) {
        productsPage.toCartPage();
        Double total = 0.0;
        ListOfWebElementFacades items = findAll(".cart_item");
        for (WebElementFacade item : items) {
            String itemName = item.findBy(".inventory_item_name").getText();
            Integer itemQuantity = Integer.parseInt(item.findBy(".cart_quantity").getText());
            Double itemPrice = Double.parseDouble(item.findBy(".inventory_item_price").getText().split("\\$")[1]);

            Double subTotal = itemQuantity * itemPrice;
            total += subTotal;

            String content = "Quantity: " + itemQuantity + "   " +  "Unit Price: " + itemPrice;
            recordDataReport(itemName, content);
        }
        assertThat(total).isEqualTo(cartTotal);
    }

    @Step
    public void checkout() {
        checkoutButton.click();
    }
}
