package com.tewhem.learnEnglish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

public class HomePanel extends JPanel {

    private JFileChooser fileChooser;
    private JButton btnChooseSrt;
    private FileProcess fileProcess;

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new Color(254, 68, 3));

        btnChooseSrt = new JButton();
        fileChooser = new JFileChooser();
        ImageIcon icon = new ImageIcon("button.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        btnChooseSrt.setIcon(icon);
        btnChooseSrt.setBackground(new Color(254, 68, 3));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        btnChooseSrt.setBorder(emptyBorder);
        btnChooseSrt.setFont(new Font("Dubai", 2, 14));
        btnChooseSrt.setForeground(new Color(3, 2, 0));
        btnChooseSrt.setText("Choose the srt file");
        btnChooseSrt.setHorizontalTextPosition(SwingConstants.CENTER);
        btnChooseSrt.addActionListener(this::btnChooseSrtActionPerformed);
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
                return extension.equals("srt");

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

            fileProcess = new FileProcess(file);

            fileProcess.readSrtFile();

            System.out.println(fileProcess.getWords());
            System.out.println();
        }
    }
}