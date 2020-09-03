package ua.com.epam.layers.businesslayer;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.inject.Inject;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ua.com.epam.api.service.GmailService;
import ua.com.epam.api.service.GmailServiceImpl;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.layers.pages.GmailBasePage;
import ua.com.epam.utils.entity.GmailCredentials;
import ua.com.epam.utils.entity.Letter;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Log4j2
public class CommonAction {
    @Inject
    private GmailBasePage basePage;

    @Step("set cookies to webDriver")
    public void setCookies(User user) {
        user.getCookies().forEach(cookie -> DriverContainer.getDriver().manage().addCookie(cookie));
    }

    @Step("is message displayed")
    public boolean isDisplayedMessage() {
        return basePage.isDisplayedMessage();
    }

    @Step("is text [{expectedText}] present in message")
    public boolean isTextPresentInMessage(String expectedText) {
        return basePage.isTextPresentInMessage(expectedText);
    }


    @Step(" create test letter")
    public void setTestLetter(Letter letter) {
        try {
            GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
            GmailCredentials userApi = FileManager.getUserApi();
            gmailService.setGmailCredentials(userApi);
            gmailService.sendMessage(letter.getRecipient(), letter.getSubject(), letter.getMessage());
        } catch (GeneralSecurityException | IOException | MessagingException e) {
            log.error(e.getMessage());
        }
    }
}
