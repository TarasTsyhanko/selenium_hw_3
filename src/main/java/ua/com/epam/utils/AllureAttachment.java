package ua.com.epam.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import ua.com.epam.factory.DriverContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AllureAttachment {
    @Attachment(value = "logs", type = "text/plain", fileExtension = ".log")
    public static byte[] addFileToAllure(String path) throws IOException {
        File file = new File(path);
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] addScreenToAllure() {
        return ((TakesScreenshot) DriverContainer.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void addBrowserLog() {
        List<LogEntry> log = DriverContainer.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        Allure.addAttachment("Browser.log", log.toString());
    }
}
