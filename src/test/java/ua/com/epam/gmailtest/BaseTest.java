package ua.com.epam.gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.*;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.guice.GmailApiModule;
import ua.com.epam.api.service.GmailClient;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.utils.allure.AllureAttachment;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.FileManager;

import java.io.IOException;

@Guice(modules = {PageModule.class, ActionModule.class, AsserterModule.class, GmailApiModule.class})
@Listeners(GmailTestListeners.class)
public class BaseTest {
    @Inject
    protected GmailClient gmailClient;

    @BeforeMethod
    public void setUp(Object[] testArg) {
        gmailClient.setTestLetters(FileManager.getLetters(), (User) testArg[0]);
        DriverContainer.getDriver().get(ConfigProperties.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Object[] testArg) throws IOException {
        DriverContainer.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
        gmailClient.clearGmailApi((User)testArg[0]);
    }
}
