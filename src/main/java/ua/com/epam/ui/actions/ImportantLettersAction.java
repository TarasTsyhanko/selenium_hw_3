package ua.com.epam.ui.actions;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import ua.com.epam.decorator.elements.PageElementCollection;
import ua.com.epam.decorator.elements.elementimpl.CheckBoxElement;
import ua.com.epam.factory.Wait;
import ua.com.epam.ui.pages.GmailBasePage;
import ua.com.epam.ui.pages.GmailImportantLettersPage;

import static ua.com.epam.utils.constant.URLConstants.IMPORTANT_LIST_URL_CONTAINS;

public class ImportantLettersAction {
    @Inject
    private GmailBasePage basePage;
    @Inject
    private GmailImportantLettersPage importantLettersPage;

    @Step("move [{n}] letters to important list")
    public void moveNLettersToImportantList(int n) {
        PageElementCollection<CheckBoxElement> checkBoxes = basePage.getListLettersCheckBox().waitUntilPresent();
        for (int i = 0; i < n; i++) {
            checkBoxes.get(i).setCheck(true);
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
    public void clearImportantLetterList() {
        Wait.forUrlContains(IMPORTANT_LIST_URL_CONTAINS);
        importantLettersPage.getImportantLettersCheckBox()
                .waitUntilPresent()
                .forEach(checkBox -> checkBox.setCheck(true));
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
