package ua.com.epam.custumpagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ua.com.epam.layers.elements.PageElement;
import ua.com.epam.layers.elements.PageElementCollection;
import ua.com.epam.layers.elements.PageElementList;


public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<PageElement> clazz;
    private final Field field;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<PageElement> clazz, Field field) {
        this.locator = locator;
        this.clazz = clazz;
        this.field = field;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> list = locator.findElements();

        By by = (new Annotations(field)).buildBy();

        PageElementCollection<PageElement> customs = new PageElementList<>(by, clazz);
        list.forEach(element -> customs.add(WrapperFactory.createInstance(clazz, element, field)));
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw e.getCause();
        }
    }
}