package de.gwasch.code.demolibrary.interfaces.authentication.model;

import de.gwasch.code.demolibrary.interfaces.library.model.User;

public interface UserAuthentification extends User {

    void init(String password);

    String getPassword();

    void setPassword(String password);
}
