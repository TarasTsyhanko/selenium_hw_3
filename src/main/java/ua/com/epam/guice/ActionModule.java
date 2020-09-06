package ua.com.epam.guice;

import com.google.inject.AbstractModule;
import ua.com.epam.ui.actions.CommonAction;
import ua.com.epam.ui.actions.ImportantLettersAction;
import ua.com.epam.ui.actions.LoginAction;

public class ActionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginAction.class);
        bind(CommonAction.class);
        bind(ImportantLettersAction.class);
    }
}
