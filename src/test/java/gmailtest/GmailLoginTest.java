package gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.Test;
import ua.com.epam.layers.businesslayer.LoginAction;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;
import ua.com.epam.validators.LoginAsserter;

public class GmailLoginTest extends BaseTest {
    @Inject
    private LoginAction loginAction;
    @Inject
    private LoginAsserter loginAsserter;

    @Test(description = "log in to gmail account")
    public void logInTpGmailAccountTestCase() {
        User user = FileManager.getUsers().get(0);
        loginAction.logInToGmailAccount(user.getLogin(), user.getPassword());
        loginAsserter.verifyIsUserNameCorrect(loginAction.getUserFullName(), user.getFullName());
    }
}
