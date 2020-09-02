package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.layers.pages.CreateMessagePage;
import ua.com.epam.layers.pages.GmailBasePage;
import ua.com.epam.layers.pages.GmailLoginPage;

public class PageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GmailLoginPage.class);
        bind(GmailBasePage.class);
        bind(CreateMessagePage.class);
    }
}
