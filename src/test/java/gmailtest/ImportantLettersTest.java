package gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.factory.DriverFactory;
import ua.com.epam.pages.GmailBasePage;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.UserFileManager;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.validators.ImportantLettersValidator;

import static ua.com.epam.utils.MessageConstant.SUCCESSFUL_DELETION_MESSAGE;
import static ua.com.epam.utils.MessageConstant.SUCCESSFUL_MOVING_MESSAGE;

public class ImportantLettersTest extends BaseTest {
    @Inject
    private ImportantLettersValidator messagesValidator;

    @BeforeMethod
    public void setCookies() {
        User user = UserFileManager.readFile().get(0);
        user.getCookies().forEach(cookie -> DriverFactory.getDriver().manage().addCookie(cookie));
        DriverFactory.getDriver().navigate().to(ConfigProperties.getBaseUrl());
    }

    @Test(description = "mark messages like important and delete it ")
    public void markMessagesLikeImportant() {
        GmailBasePage basePage = new GmailBasePage();
        basePage.markLetters(ConfigProperties.getSizeOfMarkMessages());
        basePage.moveToImportant();
        messagesValidator
                .verifyMessagesMovedToImportant(basePage.getMessageText(), SUCCESSFUL_MOVING_MESSAGE);
        basePage.openImportantLettersList();
        basePage.markAllImportantLetters();
        basePage.deleteImportantLetters();
        messagesValidator
                .verifyMessagesDeleted(basePage.getMessageText(), SUCCESSFUL_DELETION_MESSAGE);
    }
}
