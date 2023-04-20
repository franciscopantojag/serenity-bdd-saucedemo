package saucedemo.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    WebElementFacade usernameInput;

    @FindBy(id = "password")
    WebElementFacade passwordInput;

    @FindBy(id = "login-button")
    WebElementFacade loginButton;

    @Step
    public void login (String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
