package ua.com.epam.asserters;

import io.qameta.allure.Step;
import org.testng.Assert;

public class LoginAsserter {

    @Step("assert user name is correct")
    public void assertIsUserNameCorrect(String actualUserName, String expectedUserName) {
        Assert.assertEquals(actualUserName, expectedUserName,
                String.format("Expected userName [%s], but found [%s]", expectedUserName, actualUserName));
    }
}
