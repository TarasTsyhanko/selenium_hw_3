package gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.Test;
import ua.com.epam.pages.GmailBasePage;
import ua.com.epam.pages.GmailLoginPage;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.UserFileManager;
import ua.com.epam.validators.LoginValidator;

public class GmailLoginTest extends BaseTest {
    @Inject
    private LoginValidator loginValidator;

    @Test(description = "log in to gmail account")
    public void logInTpGmailAccountTestCase() {
        User user = UserFileManager.readFile().get(0);
        GmailLoginPage loginPage = new GmailLoginPage();
        loginPage.inputLoginAndClickNext(user.getLogin());
        GmailBasePage basePage = loginPage.inputPasswordAndClickNext(user.getPassword());
        String actualUserName = basePage.getUserFullName();
        loginValidator.verifyIsUserNameCorrect(actualUserName, user.getFullName());

    }
}
