package ua.com.epam.gmailtest;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.com.epam.asserters.ImportantLetterAsserter;
import ua.com.epam.ui.actions.ImportantLettersAction;
import ua.com.epam.ui.actions.LoginAction;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;

import static ua.com.epam.utils.constant.MessageConstant.*;

public class ImportantLettersTest extends BaseTest {
    @Inject
    private ImportantLettersAction importantListAction;
    @Inject
    private ImportantLetterAsserter letterAsserter;
    @Inject
    private LoginAction loginAction;

    @Test(description = "mark messages like important and delete it ", dataProvider = "users")
    public void markMessagesLikeImportant(User user) {
        loginAction.logInToGmailAccount(user.getLogin(), user.getPassword());

        importantListAction.moveNLettersToImportantList(ConfigProperties.getSizeOfMarkMessages());
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_MOVING_MESSAGE);

        importantListAction.openImportantLetterList();
        importantListAction.markAllImportantLetters();
        importantListAction.deleteMarkedLetters();
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_DELETION_MESSAGE);
    }

    @DataProvider(parallel = true)
    public Object[] users() {
        return FileManager.getUsers().toArray();
    }
}
