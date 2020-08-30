package ua.com.epam.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class Wait {
    public static <T> T until(Function<? super WebDriver, T> isTrue) {
        return DriverFactory.runWithZeroImplicitly(() ->
                new WebDriverWait(DriverFactory.getDriver(), 20).until(isTrue));
    }
}
