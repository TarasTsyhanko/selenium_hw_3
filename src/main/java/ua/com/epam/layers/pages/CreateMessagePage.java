package ua.com.epam.layers.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.layers.elements.elementimpl.ButtonElement;
import ua.com.epam.layers.elements.elementimpl.InputElement;


public class CreateMessagePage extends AbstractPage {

    @FindBy(name = "to")
    private InputElement recipientsInput;

    @FindBy(name = "subjectbox")
    private InputElement subjectInput;

    @FindBy(css = ".Ar.Au .editable")
    private InputElement massageInput;

    @FindBy(css = "div.dC")
    private ButtonElement sendMassageButton;


    public void writeRecipient(String recipients) {
       recipientsInput.waitUntilVisible().sendKeys(recipients);
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
