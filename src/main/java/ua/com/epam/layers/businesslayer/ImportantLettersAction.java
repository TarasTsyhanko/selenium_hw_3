package ua.com.epam.layers.businesslayer;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import ua.com.epam.layers.pages.GmailBasePage;

public class ImportantLettersAction {
    @Inject
    private GmailBasePage basePage;

    @Step("move [{n}] letters to important list")
    public void moveNLettersToImportantList(int n) {
        basePage.markNLetters(n);
        basePage.moveToImportant();
    }

    @Step("open important letters list")
    public void openImportantLetterList() {
        basePage.openImportantLettersList();
    }

    @Step("delete important letters")
    public void deleteImportantLetters() {
        basePage.markAllImportantLetters();
        basePage.deleteMarkedLetters();
    }
    @Step("move all letters from important list")
    public void moveAllLettersFromImportant(){
        basePage.moveFromImportant();
    }
}
