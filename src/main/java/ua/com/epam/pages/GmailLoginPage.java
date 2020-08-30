package ua.com.epam.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.Wait;

public class GmailLoginPage extends AbstractPage {
    @FindBy(id = "identifierId")
    private WebElement loginInput;
    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordInput;
    @FindBy(css = "div.VfPpkd-RLmnJb")
    private WebElement nextButton;

    @Step("input login and click next")
    public void inputLoginAndClickNext(String login) {
        loginInput.sendKeys(login);
        nextButton.click();
    }

    @Step("input password and click next")
    public GmailBasePage inputPasswordAndClickNext(String password) {
        Wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        nextButton.click();
        return new GmailBasePage();
    }
}
