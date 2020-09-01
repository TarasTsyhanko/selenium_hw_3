package ua.com.epam.factory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.com.epam.utils.config.ConfigProperties;

import java.util.concurrent.TimeUnit;

@Log4j2
public class DriverFactory {
    public static final int IMPLICITLY_WAIT = 20;

    protected static WebDriver createDriver() {
        log.info("try to create driver");
        System.setProperty(ConfigProperties.getChromeDriver(), ConfigProperties.getDriverPath());
        WebDriver driver = new ChromeDriver();
        setWait(driver, IMPLICITLY_WAIT);
        driver.manage().window().maximize();
        log.info("driver was successfully created");
        return driver;
    }

    public static void setWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
