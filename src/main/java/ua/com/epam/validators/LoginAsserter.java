package ua.com.epam.validators;

import io.qameta.allure.Step;
import org.testng.Assert;

public class LoginAsserter {

    @Step("verify user name is correct")
    public void verifyIsUserNameCorrect(String actualUserName, String expectedUserName) {
        Assert.assertEquals(actualUserName, expectedUserName,
                String.format("Expected userName [%s], but found [%s]", expectedUserName, actualUserName));
    }
}
