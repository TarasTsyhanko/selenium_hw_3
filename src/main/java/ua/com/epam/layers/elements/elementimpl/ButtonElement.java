package ua.com.epam.layers.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.layers.elements.PageElement;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.factory.Wait;

import static ua.com.epam.utils.constant.ScriptConstants.SCRIPT_CLICK;

public class ButtonElement extends PageElement {
    public ButtonElement(WebElement element, By by) {
        super(element, by);
    }

    public void click() {
        refreshElement().click();
    }

    public void actionClick() {
        Actions action = new Actions(DriverContainer.getDriver());
        action.click(refreshElement()).build().perform();
    }

    public void scriptClick() {
        JavascriptExecutor executor = (JavascriptExecutor) DriverContainer.getDriver();
        executor.executeScript(SCRIPT_CLICK, webElement);
    }

    public ButtonElement waitUntilVisible() {
        webElement = Wait.until(ExpectedConditions.visibilityOf(webElement));
        return this;
    }

    public ButtonElement waitUntilClickable() {
        webElement = Wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return this;
    }

    public ButtonElement waitUntilPresent() {
        webElement = Wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }
}
