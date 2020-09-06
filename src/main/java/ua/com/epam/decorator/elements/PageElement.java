package ua.com.epam.decorator.elements;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    protected WebElement refreshElement() {
        if (isSingle) {
            return DriverContainer.getDriver().findElement(locator);
        }
        return webElement;
    }

    public void actionClick() {
        Actions action = new Actions(DriverContainer.getDriver());
        action.click(refreshElement()).build().perform();
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getAttribute(String atr) {
        return refreshElement().getAttribute(atr);
    }

    public boolean isDisplayed() {
        return refreshElement().isDisplayed();
    }

    public PageElement scrollToElement() {
        ((JavascriptExecutor) DriverContainer.getDriver())
                .executeScript(SCRIPT_SCROLL_TO_ELEMENT, refreshElement());
        return this;
    }

    public boolean waitUntilStalenessOf() {
        return Wait.until(ExpectedConditions.stalenessOf(webElement));
    }

    public String getText() {
        return refreshElement().getText();
    }

    public String getTagName() {
        return refreshElement().getTagName();
    }

    public boolean isEnabled() {
        return refreshElement().isEnabled();
    }

    public Point getLocation() {
        return refreshElement().getLocation();
    }

    public Dimension getSize() {
        return refreshElement().getSize();
    }

    public Rectangle getRect() {
        return refreshElement().getRect();
    }


    public String getCssValue(String s) {
        return refreshElement().getCssValue(s);
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
