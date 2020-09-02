package ua.com.epam.layers.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.layers.elements.PageElement;
import ua.com.epam.factory.Wait;

public class InputElement extends PageElement {
    public InputElement(WebElement element, By by) {
        super(element, by);
    }

    public void sendKeys(CharSequence... keys) {
        getWebElement().sendKeys(keys);
    }

    public void clearAndSendKeys(CharSequence... keys) {
        getWebElement().clear();
        getWebElement().sendKeys(keys);
    }

    public void clear() {
        getWebElement().clear();
    }

    public void submit() {
        getWebElement().submit();
    }

    public InputElement waitUntilVisible() {
        Wait.until(ExpectedConditions.visibilityOf(getWebElement()));
        return this;
    }

    public InputElement waitUntilClickable() {
        webElement = Wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return this;
    }

    public InputElement waitUntilPresent() {
        webElement = Wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }

    public boolean waitUntilStalenessOf() {
        return Wait.until(ExpectedConditions.stalenessOf(webElement));
    }
}
