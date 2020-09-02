package ua.com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.epam.factory.Wait;

import java.util.List;

import static ua.com.epam.utils.constant.LocatorConstants.*;

public class GmailBasePage extends AbstractPage {

    @FindBy(css = "a.gb_D.gb_Ua.gb_i")
    private WebElement userIcon;

    @FindBy(css = ".T-I.T-I-KE.L3")
    private WebElement compose;

    @FindBy(css = "div.gb_ub.gb_vb")
    private WebElement userFullName;

    @FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji")
    private List<WebElement> listMassagesCheckBox;

    @FindBy(css = "span.asa.bjy")
    private WebElement letterActionButton;

    @FindBy(xpath = "//*[text()='Позначити як важливе']")
    private WebElement markAsImportant;

    @FindBy(xpath = "//*[text()='Позначити як неважливе']")
    private WebElement markAsNOTImportant;

    @FindBy(css = ".pH.a9q")
    private List<WebElement> markAsImportantCheckBoxList;

    @FindBy(css = ".n6 .CJ")
    private WebElement extendMenuButton;

    @FindBy(css = ".TN.bzz.aHS-bns .J-Ke.n0")
    private WebElement importantLettersButton;

    @FindBy(css = "span.T-Jo.J-J5-Ji")
    private WebElement markAllLetters;

    @FindBy(xpath = "//div[@role='main']//*[@class='oZ-jc T-Jo J-J5-Ji T-Jo-Jp']")
    private List<WebElement> importantLettersCheckBox;

    @FindBy(xpath = "//* [@gh='tm']//*[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")
    private WebElement deleteAction;

    @FindBy(css = "span.bAq")
    private WebElement informMessage;

    public void openLetterForm() {
        compose.click();
    }

    public String getUserFullName() {
        Wait.forClickable(userIcon).click();
        return userFullName.getText();
    }

    public void markNLetters(int n) {
        Wait.untilPageToBeLoaded();
        Wait.forPresentAll(By.cssSelector(LIST_LETTERS_CHECKBOX_BY_CSS))
                .stream().limit(n)
                .forEach(WebElement::click);
    }

    public void moveToImportant() {
        letterActionButton.click();
        markAsImportant.click();
    }

    public boolean isDisplayedMessage() {
        return Wait.forPresent(By.cssSelector(INFORM_MESSAGE_BY_CSS))
                .isDisplayed();
    }

    public boolean isTextPresentInMessage(String expectedText) {
        return Wait.forTextPresent(informMessage, expectedText);
    }


    public void openImportantLettersList() {
        extendMenuButton.click();
        importantLettersButton.click();
    }

    public void markAllImportantLetters() {
        Wait.forUrlContains(IMPORTANT_LIST_URL_CONTAINS);
        Wait.forPresentAll(By.cssSelector(LIST_LETTERS_CHECKBOX_BY_CSS))
                .stream().filter(WebElement::isDisplayed).forEach(WebElement::click);
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
