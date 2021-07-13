package com.tewhem.learnEnglish;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class HomePanel extends JPanel {

    private JFileChooser fileChooser;
    private JButton btnChooseSrt;

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(Color.BLUE);

        btnChooseSrt = new JButton();
        fileChooser = new JFileChooser();

        btnChooseSrt.setBackground(new Color(213, 144, 6));
        btnChooseSrt.setFont(new Font("Dubai", 1, 14));
        btnChooseSrt.setForeground(new Color(3, 2, 0));
        btnChooseSrt.setText("Choose the srt file");
        btnChooseSrt.addActionListener(e -> btnChooseSrtActionPerformed(e));
        add(btnChooseSrt);
        btnChooseSrt.setBounds(100, 50, 200, 20);

    }

    private void btnChooseSrtActionPerformed(ActionEvent e) {
        System.out.println("Butona basıldı");
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String extension = f.getName();
                extension = extension.replaceAll(".*\\.", "");
                if (extension.equals("srt")) {
                    return true;
                }

                return false;
            }

            @Override
            public String getDescription() {
                return "*.srt files";
            }

        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            System.out.println(file.getName());
        }
    }
}