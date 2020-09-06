package ua.com.epam.guice;

import com.google.inject.AbstractModule;
import ua.com.epam.asserters.ImportantLetterAsserter;
import ua.com.epam.asserters.LoginAsserter;

public class AsserterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LoginAsserter.class);
        bind(ImportantLetterAsserter.class);
    }
}
