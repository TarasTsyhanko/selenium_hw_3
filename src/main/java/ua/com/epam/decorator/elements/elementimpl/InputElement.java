package ua.com.epam.decorator.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.decorator.elements.PageElement;
import ua.com.epam.utils.Wait;

public class InputElement extends PageElement {
    public InputElement(WebElement element, By by) {
        super(element, by);
    }

    public void sendKeys(CharSequence... keys) {
        getElement().sendKeys(keys);
    }

    public void clearAndSendKeys(CharSequence... keys) {
        getElement().clear();
        getElement().sendKeys(keys);
    }

    public void clear() {
        getElement().clear();
    }

    public void submit() {
        getElement().submit();
    }

    public InputElement waitUntilVisible() {
        Wait.until(ExpectedConditions.visibilityOf(webElement));
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
}
