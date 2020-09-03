package ua.com.epam.api.api.mail;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import ua.com.epam.api.service.GmailService;
import ua.com.epam.api.service.GmailServiceImpl;
import ua.com.epam.utils.entity.GmailCredentials;
import ua.com.epam.utils.readers.FileManager;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) {
        try {
            GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
            GmailCredentials userApi = FileManager.getUserApi();
            gmailService.setGmailCredentials(userApi);

            gmailService.sendMessage("tarastsyhanko@gmail.com", "test", "everything fine");
        } catch (GeneralSecurityException | IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
