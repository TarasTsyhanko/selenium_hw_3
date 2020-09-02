package ua.com.epam.layers.elements;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.factory.Wait;

import static ua.com.epam.utils.constant.ScriptConstants.SCRIPT_SCROLL_TO_ELEMENT;

public abstract class PageElement {
    protected WebElement webElement;
    protected By locator;
    private boolean isSingle;

    protected PageElement(WebElement element, By by) {
        this.webElement = element;
        this.locator = by;
    }

    protected WebElement getWebElement() {
        if (isSingle) {
            return DriverContainer.getDriver().findElement(locator);
        }
        return webElement;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getAttribute(String atr) {
        return getWebElement().getAttribute(atr);
    }

    public boolean isDisplayed() {
        try {
            return getWebElement().isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public PageElement scrollToElement() {
        ((JavascriptExecutor) DriverContainer.getDriver())
                .executeScript(SCRIPT_SCROLL_TO_ELEMENT, getWebElement());
        return this;
    }

    public String getText() {
        return getWebElement().getText();
    }

    public String getTagName() {
        return getWebElement().getTagName();
    }

    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    public Point getLocation() {
        return getWebElement().getLocation();
    }

    public Dimension getSize() {
        return getWebElement().getSize();
    }

    public Rectangle getRect() {
        return getWebElement().getRect();
    }


    public String getCssValue(String s) {
        return getWebElement().getCssValue(s);
    }

    public <T extends PageElement> PageElement waitUntilVisible() {
        Wait.until(ExpectedConditions.visibilityOf(webElement));
        return this;
    }

    public PageElement waitUntilClickable() {
        webElement = Wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return this;
    }

    public PageElement waitUntilPresent() {
        webElement = Wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }

    public boolean waitUntilTextPresent(String text) {
        return Wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
}
