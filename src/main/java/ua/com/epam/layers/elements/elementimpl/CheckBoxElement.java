package ua.com.epam.layers.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBoxElement extends ButtonElement {
    public CheckBoxElement(WebElement element, By by) {
        super(element, by);
    }

    public void setCheck(boolean value) {
        while (value != isSelected()) {
            getWebElement().click();
        }
    }

    public boolean isSelected() {
        return getWebElement().isSelected();
    }
}
