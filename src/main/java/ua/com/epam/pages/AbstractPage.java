package ua.com.epam.pages;

import org.openqa.selenium.support.PageFactory;
import ua.com.epam.factory.DriverFactory;

public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
}
