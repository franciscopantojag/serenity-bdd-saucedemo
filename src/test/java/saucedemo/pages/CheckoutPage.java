package saucedemo.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    WebElementFacade firstNameInput;
    @FindBy(id = "last-name")
    WebElementFacade lastNameInput;
    @FindBy(id = "postal-code")
    WebElementFacade postalCodeInput;
    @FindBy(id = "continue")
    WebElementFacade continueButton;

    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[2]/div[6]")
    WebElementFacade summarySubtotalLabel;


    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[2]/div[8]")
    WebElementFacade summaryTotalLabel;


    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[2]/div[7]")
    WebElementFacade summaryTaxLabel;

    @FindBy(id = "finish")
    WebElementFacade finishButton;

    @FindBy(xpath = "//*[@id='checkout_complete_container']/h2")
    WebElementFacade successMessageElement;


    @Step
    public void fillPersonalDataAndContinue(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        continueButton.click();
    }

    @Step
    public void verifyTotals(Double cartTotal, Double checkoutTotal, Double taxTotal) {
        Double summarySubTotal = getAmountFromSelector(summarySubtotalLabel);
        assertThat(summarySubTotal).isEqualTo(cartTotal);

        Double summaryTotal = getAmountFromSelector(summaryTotalLabel);
        assertThat(summaryTotal).isEqualTo(checkoutTotal);

        Double summaryTax = getAmountFromSelector(summaryTaxLabel);
        assertThat(summaryTax).isEqualTo(taxTotal);

        // We scroll here because we want to show the totals on the screenshot
        scrollTo(0, 850);
    }

    @Step
    public void showCheckoutInfo() {
        ListOfWebElementFacades vls = findAll("//div[@class='summary_value_label']");

        recordDataReport("Payment Info", vls.get(0).getText());
        recordDataReport("Shipping Info", vls.get(1).getText());


        String subTotal = summarySubtotalLabel.getText();
        String taxTotal = summaryTaxLabel.getText();
        String total = summaryTotalLabel.getText();

        String priceInfo = String.join("   ", subTotal, taxTotal, total);
        recordDataReport("Price total", priceInfo);
    }

    @Step
    public void finishBuyAndVerifySuccess (String message) {
        finishButton.click();
        String successMessage = successMessageElement.getText();
        assertThat(successMessage).isEqualTo(message);
    }
}
