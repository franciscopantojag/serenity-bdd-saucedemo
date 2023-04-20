package saucedemo.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;

public class BasePage extends PageObject {
    public void recordDataReport (String title, String content) {
        Serenity.recordReportData().withTitle(title).andContents(content);
    }

    public Double getAmountFromSelector (WebElementFacade element) {
        return Double.parseDouble(element.getText().split("\\$")[1]);
    }

    public void scrollTo(Integer x, Integer y) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = "window.scrollTo(" + x + ", " + y + ")";
        js.executeScript(script);
    }

    public void scrollToTheTop() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, 0)");
    }
}
