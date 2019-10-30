package ua.nulp.view.cipher;

import javax.swing.*;

public interface CipherPanel {
    String getKey();
    JButton getDecodeButton();
    JButton getEncodeButton();
}
