package gmailtest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import ua.com.epam.factory.DriverProvider;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.modules.ActionModule;
import ua.com.epam.modules.AsserterModule;
import ua.com.epam.modules.PageModule;
import ua.com.epam.utils.AllureAttachment;
import ua.com.epam.utils.config.ConfigProperties;

import java.io.IOException;

@Guice(modules = {PageModule.class, ActionModule.class,AsserterModule.class})
@Listeners(GmailTestListeners.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverProvider.getDriver().get(ConfigProperties.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        DriverProvider.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }
}
