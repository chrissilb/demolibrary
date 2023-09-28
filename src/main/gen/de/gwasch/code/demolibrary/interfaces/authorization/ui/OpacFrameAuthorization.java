package de.gwasch.code.demolibrary.interfaces.authorization.ui;

import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;

public interface OpacFrameAuthorization extends OpacFrame {

    void showError();

    void showLateError();
}
