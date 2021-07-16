package com.tewhem.learnEnglish;

import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class HomeScreen extends JFrame {

    public HomeScreen(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(new Rectangle(500, 200, 300, 270));
        setResizable(false);
        HomePanel panel = new HomePanel();
        add(panel);
    }

}