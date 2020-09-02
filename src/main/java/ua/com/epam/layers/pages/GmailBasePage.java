package ua.com.epam.layers.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.layers.elements.PageElementCollection;
import ua.com.epam.layers.elements.elementimpl.ButtonElement;
import ua.com.epam.layers.elements.elementimpl.CheckBoxElement;
import ua.com.epam.layers.elements.elementimpl.LabelElement;
import ua.com.epam.factory.Wait;


import static ua.com.epam.utils.constant.LocatorConstants.*;

public class GmailBasePage extends AbstractPage {

    @FindBy(css = "a.gb_D.gb_Ua.gb_i")
    private ButtonElement userIcon;

    @FindBy(css = ".T-I.T-I-KE.L3")
    private ButtonElement compose;

    @FindBy(css = "div.gb_ub.gb_vb")
    private LabelElement userFullName;

    @FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji")
    private PageElementCollection<CheckBoxElement> listMassagesCheckBox;

    @FindBy(css = "span.asa.bjy")
    private ButtonElement letterActionButton;

    @FindBy(xpath = "//*[text()='Позначити як важливе']")
    private ButtonElement markAsImportant;

    @FindBy(xpath = "//*[text()='Позначити як неважливе']")
    private ButtonElement markAsNOTImportant;

    @FindBy(css = ".pH.a9q")
    private PageElementCollection<CheckBoxElement> markAsImportantCheckBoxList;

    @FindBy(css = ".n6 .CJ")
    private ButtonElement extendMenuButton;

    @FindBy(css = ".TN.bzz.aHS-bns .J-Ke.n0")
    private ButtonElement importantLettersButton;

    @FindBy(css = "span.T-Jo.J-J5-Ji")
    private CheckBoxElement markAllLetters;

    @FindBy(xpath = "//div[@role='main']//*[@class='oZ-jc T-Jo J-J5-Ji T-Jo-Jp']")
    private PageElementCollection<CheckBoxElement> importantLettersCheckBox;

    @FindBy(xpath = "//* [@gh='tm']//*[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")
    private ButtonElement deleteAction;

    @FindBy(css = "span.bAq")
    private LabelElement informMessage;

    public void openLetterForm() {
        compose.click();
    }

    public String getUserFullName() {
        userIcon.waitUntilClickable().click();
        return userFullName.getText();
    }

    public void markNLetters(int n) {
        Wait.untilPageToBeLoaded();
        listMassagesCheckBox.getWithLimit(n)
                .forEach(CheckBoxElement::scriptClick);
    }

    public void moveToImportant() {
        letterActionButton.click();
        markAsImportant.click();
    }

    public boolean isDisplayedMessage() {
        return informMessage.waitUntilPresent().isDisplayed();
    }

    public boolean isTextPresentInMessage(String expectedText) {
        return informMessage.waitUntilTextPresent(expectedText);
    }


    public void openImportantLettersList() {
        extendMenuButton.click();
        importantLettersButton.click();
    }

    public void markAllImportantLetters() {
        Wait.forUrlContains(IMPORTANT_LIST_URL_CONTAINS);
        listMassagesCheckBox
                .stream()
                .filter(CheckBoxElement::isDisplayed)
                .forEach(CheckBoxElement::scriptClick);
    }

    public void moveFromImportant() {
       markAllLetters.click();
       letterActionButton.click();
       markAsNOTImportant.click();
       markAllLetters.click();
    }

    public void deleteMarkedLetters() {
        deleteAction.click();
    }

}
