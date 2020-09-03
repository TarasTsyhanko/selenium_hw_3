package gmailtest;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.layers.businesslayer.CommonAction;
import ua.com.epam.layers.businesslayer.ImportantLettersAction;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;
import ua.com.epam.utils.config.ConfigProperties;

import static ua.com.epam.utils.constant.MessageConstant.*;

public class ImportantLettersTest extends BaseTest {
    @Inject
    private ImportantLettersAction importantListAction;
    @Inject
    private CommonAction commonAction;

    @BeforeMethod
    public void setCookies() {
        User testUser = FileManager.getUsers().get(0);
        FileManager.getLetters().forEach(letter -> {
            letter.setRecipient(testUser.getLogin());
            commonAction.setTestLetter(letter);
        });
        commonAction.setCookies(testUser);
        DriverContainer.getDriver().navigate().to(ConfigProperties.getBaseUrl());
    }

    @Test(description = "mark messages like important and delete it ")
    public void markMessagesLikeImportant() {
        importantListAction.moveNLettersToImportantList(ConfigProperties.getSizeOfMarkMessages());
        Assert.assertTrue(commonAction.isDisplayedMessage());
        Assert.assertTrue(commonAction.isTextPresentInMessage(SUCCESSFUL_MOVING_MESSAGE));

        importantListAction.openImportantLetterList();
        importantListAction.deleteImportantLetters();
        Assert.assertTrue(commonAction.isDisplayedMessage());
        Assert.assertTrue(commonAction.isTextPresentInMessage(SUCCESSFUL_DELETION_MESSAGE));
    }
}
