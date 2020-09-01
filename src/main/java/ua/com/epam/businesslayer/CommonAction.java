package ua.com.epam.businesslayer;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import ua.com.epam.factory.DriverProvider;
import ua.com.epam.pages.GmailBasePage;
import ua.com.epam.utils.entity.User;

public class CommonAction {
    @Inject
    private GmailBasePage basePage;

    @Step("set cookies to webDriver")
    public void setCookies(User user){
        user.getCookies().forEach(cookie -> DriverProvider.getDriver().manage().addCookie(cookie));
    }

    @Step("is message displayed")
    public boolean isDisplayedMessage() {
        return basePage.isDisplayedMessage();
    }

    @Step("is text [{expectedText}] present in message")
    public boolean isTextPresentInMessage(String expectedText) {
        return basePage.isTextPresentInMessage(expectedText);
    }
}
