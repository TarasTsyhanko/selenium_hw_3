package ua.com.epam.layers.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.layers.elements.elementimpl.ButtonElement;
import ua.com.epam.layers.elements.elementimpl.InputElement;

public class GmailLoginPage extends AbstractPage {
    @FindBy(id = "identifierId")
    private InputElement loginEInput;
    @FindBy(name = "password")
    private InputElement passwordInput;
    @FindBy(css = "div.VfPpkd-RLmnJb")
    private ButtonElement nextButton;

    public void inputLoginAndClickNext(String login) {
        loginEInput.sendKeys(login);
        nextButton.click();
    }

    public void inputPasswordAndClickNext(String password) {
        passwordInput.waitUntilPresent().sendKeys(password);
        nextButton.actionClick();
    }
}
