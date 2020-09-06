package ua.com.epam.ui.actions;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.inject.Inject;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ua.com.epam.api.service.GmailService;
import ua.com.epam.api.service.GmailServiceImpl;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.ui.pages.GmailBasePage;
import ua.com.epam.utils.entity.GmailCredentials;
import ua.com.epam.utils.entity.Letter;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Log4j2
public class CommonAction {
    @Inject
    private GmailBasePage basePage;

    @Step("set cookies to webDriver")
    public void setCookies(User user) {
        user.getCookies().forEach(cookie -> DriverContainer.getDriver().manage().addCookie(cookie));
    }

    @Step("create test letter")
    public void setTestLetters(List<Letter> letters, User recipient) throws GeneralSecurityException, IOException {
        GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
        gmailService.setGmailCredentials(FileManager.getUserApi());
        letters.forEach(letter -> {
            try {
                gmailService.sendMessage(recipient.getLogin(), letter.getSubject(), letter.getMessage());
            } catch (MessagingException | IOException e) {
                log.error(e.getStackTrace());
            }
        });

    }
}
