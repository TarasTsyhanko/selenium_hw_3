package ua.com.epam.layers.businesslayer;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import ua.com.epam.layers.pages.GmailBasePage;
import ua.com.epam.layers.pages.GmailLoginPage;

public class LoginAction {

    @Inject
    private GmailLoginPage loginPage;

    @Inject
    private GmailBasePage basePage;

    @Step("log in to gmail account")
    public void logInToGmailAccount(String login, String password) {
        loginPage.inputLoginAndClickNext(login);
        loginPage.inputPasswordAndClickNext(password);
    }

    @Step("get user full name")
    public String getUserFullName() {
        return basePage.getUserFullName();
    }
}
