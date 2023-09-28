package de.gwasch.code.demolibrary.interfaces.authentication.ui;

import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;

public interface OpacFrameAuthentification extends OpacFrame {

    void startLowTrafficObservation();

    void stopLowTrafficObservation();

    void startHighTrafficObservation();

    void stopHighTrafficObservation();

    void initView();

    void updateView();

    boolean login();

    void loginSupport();

    void startAutoLogout();

    void stopAutoLogout();

    void doCountdown();

    void stopCountdown();

    void startCountdown();

    void after1();

    void after2();

    void delay();
}
