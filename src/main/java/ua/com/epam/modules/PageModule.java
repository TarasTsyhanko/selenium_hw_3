package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.pages.CreateMessagePage;
import ua.com.epam.pages.GmailBasePage;
import ua.com.epam.pages.GmailLoginPage;

public class PageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GmailLoginPage.class);
        bind(GmailBasePage.class);
        bind(CreateMessagePage.class);
    }
}
