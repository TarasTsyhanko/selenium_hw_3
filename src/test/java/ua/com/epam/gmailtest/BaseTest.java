package ua.com.epam.gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.*;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.ui.actions.CommonAction;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.utils.AllureAttachment;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.readers.FileManager;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Guice(modules = {PageModule.class, ActionModule.class, AsserterModule.class})
@Listeners(GmailTestListeners.class)
public class BaseTest {
    @Inject
    private CommonAction commonAction;

    @BeforeMethod
    public void setUp(Object[] testArgs) throws GeneralSecurityException, IOException {
        User testUser = (User) testArgs[0];
        commonAction.setTestLetters(FileManager.getLetters(), testUser);
        DriverContainer.getDriver().get(ConfigProperties.getBaseUrl());
      //  commonAction.setCookies(testUser);
       // DriverContainer.getDriver().navigate().to(ConfigProperties.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        DriverContainer.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }
}
