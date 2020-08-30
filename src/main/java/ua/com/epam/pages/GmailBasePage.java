package ua.com.epam.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.Wait;

import java.util.List;

public class GmailBasePage extends AbstractPage {

    @FindBy(css = "a.gb_D.gb_Ua.gb_i")
    private WebElement userIcon;

    @FindBy(css = "div.gb_ub.gb_vb")
    private WebElement userFullName;

    @FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji")
    private List<WebElement> listMassagesCheckBox;

    @FindBy(css = "span.asa.bjy")
    private WebElement letterActionButton;

    @FindBy(xpath = "//*[text()='Позначити як важливе']")
    private WebElement markAsImportant;

    @FindBy(xpath = "//*[@class='CJ' and contains(text(),'Більше')]")
    private WebElement extendMenuButton;

    @FindBy(xpath = "//a[text()='Важливі']")
    private WebElement importantLettersButton;

    @FindBy(xpath = "//div[@role='main']//*[@class='oZ-jc T-Jo J-J5-Ji T-Jo-Jp']")
    private List<WebElement> importantLettersCheckBox;

    @FindBy(xpath = "//* [@gh='tm']//*[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")
    private WebElement deleteAction;

    @FindBy(css = "span.bAq")
    private WebElement informMessage;

    @Step("get user full name")
    public String getUserFullName() {
        userIcon.click();
        return userFullName.getText();
    }

    @Step("mark letters using checkbox")
    public void markLetters(int size) {
        Wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.oZ-jc.T-Jo.J-J5-Ji")))
                .stream().limit(size)
                .forEach(WebElement::click);
    }

    @Step("move marked letters to important list")
    public void moveToImportant() {
        letterActionButton.click();
        markAsImportant.click();
    }

    @Step("get text from inform message")
    public String getMessageText() {
        return Wait.until(ExpectedConditions.visibilityOf(informMessage)).getText();
    }

    @Step("open important letters list")
    public void openImportantLettersList() {
        extendMenuButton.click();
        importantLettersButton.click();
    }

    @Step("mark all important letters using checkbox")
    public void markAllImportantLetters() {
        Wait.until(ExpectedConditions.urlContains("imp"));
        listMassagesCheckBox.stream().filter(WebElement::isDisplayed).forEach(WebElement::click);
    }

    @Step("delete important letters")
    public void deleteImportantLetters() {
        deleteAction.click();
    }

}
