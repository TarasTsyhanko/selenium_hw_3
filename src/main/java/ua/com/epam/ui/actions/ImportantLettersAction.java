package ua.com.epam.ui.actions;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.com.epam.decorator.WrapperFactory;
import ua.com.epam.decorator.elements.PageElementCollection;
import ua.com.epam.decorator.elements.elementimpl.CheckBoxElement;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.utils.Wait;
import ua.com.epam.ui.pages.GmailBasePage;
import ua.com.epam.ui.pages.GmailImportantLettersPage;

import java.text.CollationElementIterator;

import static ua.com.epam.constant.AttributeConstants.CLASS_VALUE;
import static ua.com.epam.constant.AttributeConstants.ATTRIBUTE_CLASS;
import static ua.com.epam.constant.LocatorsConstant.IMPORTANT_LETTER_CHECKBOX_XPATH;
import static ua.com.epam.constant.LocatorsConstant.LETTER_CHECKBOX_XPATH;
import static ua.com.epam.constant.URLConstants.IMPORTANT_LIST_URL_CONTAINS;

public class ImportantLettersAction {
    @Inject
    private GmailBasePage basePage;
    @Inject
    private GmailImportantLettersPage importantLettersPage;


    @Step(" wait letter to be loaded")
    public void waitLetterToBeLoaded(int n) {
        while (basePage.getListLettersCheckBox().size() < n) {
            Wait.untilPageToBeToBeRefreshed();
        }
    }

    @Step("move [{n}] letters to important list")
    public void moveNLettersToImportantList(int n) {
        Wait.untilPageToBeLoaded();
        for (int i = 1; i <= n; i++) {
            WebElement element = DriverContainer
                    .getDriver()
                    .findElement(By.xpath(String.format(LETTER_CHECKBOX_XPATH, i)));
            CheckBoxElement checkBox = WrapperFactory.createInstance(CheckBoxElement.class, element, null);
            checkBox.waitUntilVisible().actionClick();
            checkBox.waitUntilAttributeToBe(ATTRIBUTE_CLASS, CLASS_VALUE);
        }
        basePage.getLetterActionButton().actionClick();
        basePage.getMarkAsImportant().actionClick();
    }

    @Step("open important letters list")
    public void openImportantLetterList() {
        basePage.getExtendMenuButton().scrollToElement().actionClick();
        basePage.getImportantLettersButton().waitUntilClickable().click();
    }

    @Step("clear important letter list")
    public void clearImportantLetterList(int n) {
        Wait.forUrlContains(IMPORTANT_LIST_URL_CONTAINS);
        for (int i = 1; i <= n; i++) {
            WebElement element = DriverContainer
                    .getDriver()
                    .findElement(By.xpath(String.format(IMPORTANT_LETTER_CHECKBOX_XPATH, i)));
            CheckBoxElement checkBox = WrapperFactory.createInstance(CheckBoxElement.class, element, null);
            checkBox.waitUntilVisible().actionClick();
        }
        importantLettersPage.getDeleteAction().click();
    }

    @Step("is message displayed")
    public boolean isDisplayedMessage() {
        return basePage.getInformMessage().waitUntilVisible().isDisplayed();
    }

    @Step("get inform message text")
    public String getMessageText() {
        return basePage.getInformMessage().getText();
    }
}
