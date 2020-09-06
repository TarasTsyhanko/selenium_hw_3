package ua.com.epam.gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.*;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.ui.actions.LoginAction;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.utils.AllureAttachment;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;
import ua.com.epam.asserters.LoginAsserter;

import java.io.IOException;

@Guice(modules = {PageModule.class, ActionModule.class, AsserterModule.class})
@Listeners(GmailTestListeners.class)
public class GmailLoginTest{
    @Inject
    private LoginAction loginAction;
    @Inject
    private LoginAsserter loginAsserter;

    @Test(description = "log in to gmail account",dataProvider = "users")
    public void logInTpGmailAccountTestCase(User user) {
        loginAction.logInToGmailAccount(user.getLogin(), user.getPassword());
        loginAsserter.assertIsUserNameCorrect(loginAction.getUserFullName(), user.getFullName());
    }

    @BeforeMethod
    public void setUp() {
        DriverContainer.getDriver().get(ConfigProperties.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        DriverContainer.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }

    @DataProvider(parallel = true)
    public Object[] users(){
        return FileManager.getUsers().toArray();
    }
}
