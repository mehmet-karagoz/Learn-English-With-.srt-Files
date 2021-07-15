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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

public class HomePanel extends JPanel {

    private JFileChooser fileChooser;
    private JButton btnChooseSrt;
    private FileProcess fileProcess;
    private JTextField txtFileName;
    private JButton btnWriteUnKnownWords;

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new Color(254, 68, 3));

        btnChooseSrt = new JButton();
        fileChooser = new JFileChooser();
        var icon = new ImageIcon("button.png");
        var img = icon.getImage();
        Image newImg = img.getScaledInstance(220, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        btnChooseSrt.setIcon(icon);
        btnChooseSrt.setBackground(new Color(254, 68, 3));
        var emptyBorder = BorderFactory.createEmptyBorder();
        btnChooseSrt.setBorder(emptyBorder);
        var fontName = "Dubai";
        btnChooseSrt.setFont(new Font(fontName, 2, 14));
        btnChooseSrt.setForeground(new Color(3, 2, 0));
        btnChooseSrt.setText("Choose the srt file");
        btnChooseSrt.setHorizontalTextPosition(SwingConstants.CENTER);
        btnChooseSrt.addActionListener(this::btnChooseSrtActionPerformed);
        add(btnChooseSrt);
        btnChooseSrt.setBounds(100, 50, 200, 20);

        txtFileName = new JTextField();
        txtFileName.setFont(new Font(fontName, 2, 14));
        txtFileName.setForeground(new Color(3, 2, 0));
        txtFileName.setText("Write file name with *.txt");
        add(txtFileName);
        txtFileName.setBounds(100, 80, 200, 20);

        btnWriteUnKnownWords = new JButton();
        btnWriteUnKnownWords.setIcon(icon);
        btnWriteUnKnownWords.setBackground(new Color(254, 68, 3));
        btnWriteUnKnownWords.setBorder(emptyBorder);
        btnWriteUnKnownWords.setFont(new Font(fontName, 2, 14));
        btnWriteUnKnownWords.setText("Write unKnownWords");
        btnWriteUnKnownWords.setHorizontalTextPosition(SwingConstants.CENTER);
        btnWriteUnKnownWords
                .addActionListener(this::btnWriteUnKnownWordsActionPerformed);
        add(btnWriteUnKnownWords);
        btnWriteUnKnownWords.setBounds(100, 110, 200, 20);
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
            var file = fileChooser.getSelectedFile();

            fileProcess = new FileProcess(file);

            fileProcess.readSrtFile();

            System.out.println(fileProcess.getWords());
            System.out.println();
        }
    }

    private void btnWriteUnKnownWordsActionPerformed(ActionEvent e) {
        System.out.println("diger buton");
        String targetFileName = txtFileName.getText();

        fileProcess.writeUnKnownWords(targetFileName);

        JOptionPane.showMessageDialog(this, "UnKnownWords wrote successfully");
    }
}