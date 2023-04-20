package saucedemo;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class NavigateActions extends UIInteractions {
    @Step("Navigate to the home page")
    public void toTheSauceDemoLoginPage() {
        openUrl("https://www.saucedemo.com/");
    }

    @Step
    public void toAboutPage () {
        $("#react-burger-menu-btn").click();
        $("#about_sidebar_link").click();
    }

    @Step("Go Back to Products Page")
    public void goBackOnePage() {
        getDriver().navigate().back();
    }

}
