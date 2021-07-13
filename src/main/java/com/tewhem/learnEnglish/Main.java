package com.tewhem.learnEnglish;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            HomeScreen frame = new HomeScreen("Home");
            frame.setVisible(true);

        });
    }
}
