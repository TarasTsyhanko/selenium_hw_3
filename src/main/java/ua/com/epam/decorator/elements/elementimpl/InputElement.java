package ua.com.epam.decorator.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.decorator.elements.PageElement;
import ua.com.epam.factory.Wait;

public class InputElement extends PageElement {
    public InputElement(WebElement element, By by) {
        super(element, by);
    }

    public void sendKeys(CharSequence... keys) {
        refreshElement().sendKeys(keys);
    }

    public void clearAndSendKeys(CharSequence... keys) {
        refreshElement().clear();
        refreshElement().sendKeys(keys);
    }

    public void clear() {
        refreshElement().clear();
    }

    public void submit() {
        refreshElement().submit();
    }

    public InputElement waitUntilVisible() {
        Wait.until(ExpectedConditions.visibilityOf(refreshElement()));
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
