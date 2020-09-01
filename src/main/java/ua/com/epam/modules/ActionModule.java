package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.businesslayer.CommonAction;
import ua.com.epam.businesslayer.CreateLetterAction;
import ua.com.epam.businesslayer.ImportantLettersAction;
import ua.com.epam.businesslayer.LoginAction;

public class ActionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginAction.class);
        bind(CommonAction.class);
        bind(ImportantLettersAction.class);
        bind(CreateLetterAction.class);
    }
}
