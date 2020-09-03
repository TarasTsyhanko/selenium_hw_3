package ua.com.epam.api.service;

import ua.com.epam.utils.entity.GmailCredentials;

import javax.mail.MessagingException;
import java.io.IOException;

public interface GmailService {
    void setGmailCredentials(GmailCredentials gmailCredentials);

    boolean sendMessage(String recipient, String subject, String body) throws MessagingException, IOException;
}
