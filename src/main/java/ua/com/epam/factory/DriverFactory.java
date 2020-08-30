package ua.com.epam.factory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.com.epam.utils.config.ConfigProperties;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Log4j2
public class DriverFactory {
    private static final int IMPLICITLY_WAIT = 20;
    private static WebDriver DRIVER;

    public static WebDriver getDriver() {
        if (DRIVER == null) {
            initDriver();
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

    private static void initDriver() {
        log.info("try to create driver");
        System.setProperty(ConfigProperties.getChromeDriver(), ConfigProperties.getDriverPath());
        WebDriver driver = new ChromeDriver();
        setWait(driver, IMPLICITLY_WAIT);
        driver.manage().window().maximize();
        DRIVER = driver;
        log.info("driver was successfully created");
    }

    private static void setWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static <T> T runWithZeroImplicitly(Supplier<T> supplier) {
        setWait(DRIVER, 0);
        T element = supplier.get();
        setWait(DRIVER, IMPLICITLY_WAIT);
        return element;
    }
}
