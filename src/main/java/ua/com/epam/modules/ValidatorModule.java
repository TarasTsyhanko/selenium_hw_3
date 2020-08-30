package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.validators.ImportantLettersValidator;
import ua.com.epam.validators.LoginValidator;

public class ValidatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LoginValidator.class);
        bind(ImportantLettersValidator.class);
    }
}
