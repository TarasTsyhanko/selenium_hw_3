package ua.com.epam.businesslayer;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import ua.com.epam.pages.CreateMessagePage;
import ua.com.epam.pages.GmailBasePage;
import ua.com.epam.utils.entity.Letter;

public class CreateLetterAction {
    @Inject
    private GmailBasePage basePage;
    @Inject
    private CreateMessagePage createMessagePage;

    @Step("create letter and send")
    public void createAndSendLetter(Letter letter) {
        basePage.openLetterForm();
        createMessagePage.writeRecipient(letter.getRecipient());
        createMessagePage.writeSubject(letter.getSubject());
        createMessagePage.writeMassage(letter.getMessage());
        createMessagePage.sendLetter();
    }
}
