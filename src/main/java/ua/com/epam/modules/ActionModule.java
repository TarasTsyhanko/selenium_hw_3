package ua.com.epam.modules;

import com.google.inject.AbstractModule;
import ua.com.epam.layers.businesslayer.CommonAction;
import ua.com.epam.layers.businesslayer.CreateLetterAction;
import ua.com.epam.layers.businesslayer.ImportantLettersAction;
import ua.com.epam.layers.businesslayer.LoginAction;

public class ActionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginAction.class);
        bind(CommonAction.class);
        bind(ImportantLettersAction.class);
        bind(CreateLetterAction.class);
    }
}
