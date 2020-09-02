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

    public static WebElement forVisible(WebElement element) {
        return until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement forClickable(WebElement element) {
        return until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forPresent(By locator) {
        return until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> forPresentAll(By locator) {
        return until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static boolean forTextPresent(WebElement element, String text) {
        return until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static boolean forUrlContains(String text) {
        return until(ExpectedConditions.urlContains(text));
    }

    public static boolean forStalenessOf(WebElement element) {
        return until(ExpectedConditions.stalenessOf(element));
    }
}
