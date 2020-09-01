package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.validators.LoginAsserter;

public class AsserterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LoginAsserter.class);
    }
}
