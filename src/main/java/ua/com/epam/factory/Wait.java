package ua.com.epam.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static ua.com.epam.factory.DriverFactory.IMPLICITLY_TIME;
import static ua.com.epam.utils.constant.ScriptConstants.COMPLETE;
import static ua.com.epam.utils.constant.ScriptConstants.SCRIPT_READY_STATE;

public class Wait {
    private static final int EXPLICITLY_TIME = 20;

    private static <T> T runWithZeroImplicitly(Supplier<T> supplier) {
        DriverFactory.setWait(DriverContainer.getDriver(), 0);
        T element = supplier.get();
        DriverFactory.setWait(DriverContainer.getDriver(), IMPLICITLY_TIME);
        return element;
    }

    public static <T> T until(Function<? super WebDriver, T> isTrue) {
        return runWithZeroImplicitly(() ->
                new WebDriverWait(DriverContainer.getDriver(), EXPLICITLY_TIME).until(isTrue));
    }

    public static void untilPageToBeLoaded() {
        until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript(SCRIPT_READY_STATE).toString().equals(COMPLETE));
    }

    public static void untilPageToBeToBeRefreshed() {
        DriverContainer.getDriver().navigate().refresh();
        untilPageToBeLoaded();
    }

    public static boolean forUrlContains(String text) {
        return until(ExpectedConditions.urlContains(text));
    }

    public static boolean forUrlToRe(String url) {
        return until(ExpectedConditions.urlToBe(url));
    }

}
