package gmailtest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import ua.com.epam.factory.DriverFactory;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.modules.ValidatorModule;
import ua.com.epam.utils.AllureAttachment;
import ua.com.epam.utils.config.ConfigProperties;

import java.io.IOException;

@Guice(modules = {ValidatorModule.class})
@Listeners(GmailTestListeners.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.getDriver().get(ConfigProperties.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() throws IOException {
        DriverFactory.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }
}
