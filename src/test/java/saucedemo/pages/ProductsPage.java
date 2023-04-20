package saucedemo.pages;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPage extends BasePage {
    final String addToCartButtonsXpath =  "//button[@class='btn btn_primary btn_small btn_inventory']";


    @FindBy(xpath = "//*[@id='header_container']/div[2]/div/span/select")
    WebElementFacade productSortSelect;

    @FindBy(xpath = "//*[@id='shopping_cart_container']/a/span")
    WebElementFacade shoppingCartBadge;


    @FindBy(xpath = "//*[@id='shopping_cart_container']/a")
    WebElementFacade shoppingCartLink;

    @Step
    public void sortProductsByPriceFromLowestToHighest () {
        productSortSelect.selectByValue("lohi");
    }

    void sortProductsByPriceFromHighestToLowest() {
        productSortSelect.selectByValue("hilo");
    }

    @Step("Add the {0} most expensive products to Cart")
    public void addTheNMostExpensiveProductsToCart (Integer nItems) {
        sortProductsByPriceFromHighestToLowest();
        findAll(addToCartButtonsXpath).subList(0, nItems).forEach(WebElementFacade::click);

        // scroll up because of the screenshot
        scrollToTheTop();
    }

    @Step("Verify Cart has {0} items")
    public void verifyCartHasNItems(Integer nItems) {
        Integer cartLength = Integer.parseInt(shoppingCartBadge.getText());
        assertThat(cartLength).isEqualTo(nItems);
    }

    public void toCartPage() {
        shoppingCartLink.click();
    }
}
