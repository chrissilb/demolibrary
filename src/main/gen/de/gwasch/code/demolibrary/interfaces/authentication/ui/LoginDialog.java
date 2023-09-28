package de.gwasch.code.demolibrary.interfaces.authentication.ui;

import de.gwasch.code.demolibrary.authentication.ui.LoginDialogImpl;
import de.gwasch.code.demolibrary.interfaces.authentication.model.UserAuthentification;

public interface LoginDialog {

    void init();

    UserAuthentification getUser();

    void login();

    void sleep();

    void hurryUp();

    void cancel();

    LoginDialogImpl getLoginDialogImpl();
}
