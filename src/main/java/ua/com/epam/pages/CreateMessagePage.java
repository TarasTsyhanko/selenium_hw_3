package ua.com.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.epam.factory.Wait;


public class CreateMessagePage extends AbstractPage {

    @FindBy(name = "to")
    private WebElement recipientsInput;

    @FindBy(name = "subjectbox")
    private WebElement subjectInput;

    @FindBy(css = ".Ar.Au .editable")
    private WebElement massageInput;

    @FindBy(css = "div.dC")
    private WebElement sendMassageButton;


    public void writeRecipient(String recipients) {
        Wait.forVisible(recipientsInput).sendKeys(recipients);
    }

    public void writeSubject(String subject) {
        subjectInput.sendKeys(subject);
    }

    public void writeMassage(String massage) {
        massageInput.sendKeys(massage);
    }

    public void sendLetter() {
        sendMassageButton.click();
    }
}
