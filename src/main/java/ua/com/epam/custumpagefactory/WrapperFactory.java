package ua.com.epam.custumpagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import ua.com.epam.layers.elements.PageElement;

import java.lang.reflect.Field;

public class WrapperFactory {

    public static PageElement createInstance(Class<PageElement> clazz, WebElement element, Field field) {
        By by = (new Annotations(field)).buildBy();
        try {
            return  clazz.getConstructor(WebElement.class, By.class).newInstance(element, by);
        } catch (Exception e) {
            throw new AssertionError("PageElement can't be represented as " + clazz);
        }
    }
}