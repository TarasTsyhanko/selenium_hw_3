package ua.com.epam.pages;

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

    public void inputLoginAndClickNext(String login) {
        loginInput.sendKeys(login);
        nextButton.click();
    }

    public void inputPasswordAndClickNext(String password) {
        Wait.forVisible(passwordInput).sendKeys(password);
        nextButton.click();
    }
}
