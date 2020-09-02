package ua.com.epam.layers.pages;

import org.openqa.selenium.support.PageFactory;
import ua.com.epam.custumpagefactory.PageElementDecorator;
import ua.com.epam.factory.DriverContainer;

public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(new PageElementDecorator(DriverContainer::getDriver), this);
    }
}
