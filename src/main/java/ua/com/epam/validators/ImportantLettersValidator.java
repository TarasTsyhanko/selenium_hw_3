package ua.com.epam.validators;

import io.qameta.allure.Step;
import org.testng.Assert;

public class ImportantLettersValidator {

    @Step("verify letters were moved to important list")
    public void verifyMessagesMovedToImportant(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected)
                , String.format("Expected that letters were " +
                        "successful moved to important list, but found message [%s]", actual));
    }

    @Step("verify letters were deleted")
    public void verifyMessagesDeleted(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected)
                , String.format("Expected that letters were deleted , but found message [%s]", actual));
    }
}