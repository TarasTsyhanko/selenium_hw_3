package ua.com.epam.factory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.com.epam.utils.config.ConfigProperties;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Log4j2
public class DriverProvider {

    private static WebDriver DRIVER;

    public static WebDriver getDriver() {
        if (DRIVER == null) {
           DRIVER = DriverFactory.createDriver();
        }
        return DRIVER;
    }

    public static void quitDriver() {
        log.info("try to quit driver");
        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }
        log.info("driver was successfully closed");
    }
}
