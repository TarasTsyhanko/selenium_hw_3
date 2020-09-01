package ua.com.epam.pages;

import org.openqa.selenium.support.PageFactory;
import ua.com.epam.factory.DriverProvider;

public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
}
