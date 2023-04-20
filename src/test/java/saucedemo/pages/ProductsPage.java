package saucedemo.pages;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPage extends BasePage {
    @FindBy(xpath = "//*[@id='header_container']/div[2]/div/span/select")
    WebElementFacade productSortSelect;

    @FindBy(xpath = "//*[@id='shopping_cart_container']/a/span")
    WebElementFacade shoppingCartBadge;

    @FindBy(xpath = "//*[@id='shopping_cart_container']/a")
    WebElementFacade shoppingCartLink;

    @FindAll({@FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")})
    private List<WebElementFacade> addToCartButtons;

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
        addToCartButtons.subList(0, nItems).forEach(WebElementFacade::click);

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
