package ua.com.epam.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;
import java.util.function.Supplier;

import static ua.com.epam.factory.DriverFactory.IMPLICITLY_WAIT;

public class Wait {

    public static <T> T runWithZeroImplicitly(Supplier<T> supplier) {
        DriverFactory.setWait(DriverProvider.getDriver(), 0);
        T element = supplier.get();
        DriverFactory.setWait(DriverProvider.getDriver(), IMPLICITLY_WAIT);
        return element;
    }

    public static <T> T until(Function<? super WebDriver, T> isTrue) {
        return runWithZeroImplicitly(() ->
                new WebDriverWait(DriverProvider.getDriver(), 20).until(isTrue));
    }
}
