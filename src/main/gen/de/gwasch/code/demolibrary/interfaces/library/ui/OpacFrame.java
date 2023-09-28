package de.gwasch.code.demolibrary.interfaces.library.ui;

import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.demolibrary.library.ui.OpacFrameImpl;
import javax.swing.JMenu;

public interface OpacFrame {

    void init(Library library);

    void initView();

    void help();

    JMenu getFileMenu();

    User getUser();

    void setUser(User user);

    Library getLibrary();

    void lendBook();

    void selectBook();

    void updateView();

    OpacFrameImpl getOpacFrameImpl();
}
