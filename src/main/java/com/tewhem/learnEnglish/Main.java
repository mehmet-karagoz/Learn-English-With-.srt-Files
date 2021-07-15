package com.tewhem.learnEnglish;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            var frame = new HomeScreen("Home");
            frame.setVisible(true);

        });
    }
}
