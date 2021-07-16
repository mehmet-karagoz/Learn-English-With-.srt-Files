package com.tewhem.learnEnglish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    private JLabel lblQuestion;
    private JButton btnYes;
    private JButton btnNo;

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
        btnChooseSrt.setBounds(40, 50, 200, 20);

        txtFileName = new JTextField();
        txtFileName.setFont(new Font(fontName, 2, 14));
        txtFileName.setForeground(new Color(3, 2, 0));
        txtFileName.setText("Write file name with *.txt");
        add(txtFileName);
        txtFileName.setBounds(40, 80, 200, 20);

        btnWriteUnKnownWords = new JButton();
        btnWriteUnKnownWords.setIcon(icon);
        btnWriteUnKnownWords.setBackground(new Color(254, 68, 3));
        btnWriteUnKnownWords.setBorder(emptyBorder);
        btnWriteUnKnownWords.setForeground(new Color(3, 2, 0));
        btnWriteUnKnownWords.setFont(new Font(fontName, 2, 14));
        btnWriteUnKnownWords.setText("Write unKnownWords");
        btnWriteUnKnownWords.setHorizontalTextPosition(SwingConstants.CENTER);
        btnWriteUnKnownWords
                .addActionListener(this::btnWriteUnKnownWordsActionPerformed);
        add(btnWriteUnKnownWords);
        btnWriteUnKnownWords.setBounds(40, 110, 200, 20);

        lblQuestion = new JLabel();
        lblQuestion.setFont(new Font(fontName, 2, 14));
        lblQuestion.setForeground(new Color(3, 2, 0));
        String question =
                "Do you want to change color of unKnownWords in *.srt file?";
        String html =
                "<html><body style='width: %1spx'><p style=\"font-family: 'Dubai'; font-size: 14; font-style: italic;\">%1s</p>";
        lblQuestion.setText(String.format(html, 200, question));
        lblQuestion.setHorizontalTextPosition(SwingConstants.CENTER);
        add(lblQuestion);
        lblQuestion.setBounds(40, 140, 200, 40);

        newImg = img.getScaledInstance(110, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        btnYes = new JButton();
        btnYes.setIcon(icon);
        btnYes.setBackground(new Color(254, 68, 3));
        btnYes.setBorder(emptyBorder);
        btnYes.setForeground(new Color(3, 2, 0));
        btnYes.setFont(new Font(fontName, 2, 14));
        btnYes.setText("YES");
        btnYes.setHorizontalTextPosition(SwingConstants.CENTER);
        btnYes.addActionListener(this::btnYesActionPerformed);
        add(btnYes);
        btnYes.setBounds(40, 190, 90, 20);

        btnNo = new JButton();
        btnNo.setIcon(icon);
        btnNo.setBackground(new Color(254, 68, 3));
        btnNo.setBorder(emptyBorder);
        btnNo.setForeground(new Color(3, 2, 0));
        btnNo.setFont(new Font(fontName, 2, 14));
        btnNo.setText("NO");
        btnNo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNo.addActionListener(this::btnNoActionPerformed);
        add(btnNo);
        btnNo.setBounds(150, 190, 90, 20);
    }

    private void btnChooseSrtActionPerformed(ActionEvent e) {
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
        }
    }

    private void btnWriteUnKnownWordsActionPerformed(ActionEvent e) {
        String targetFileName = txtFileName.getText();

        fileProcess.writeUnKnownWords(targetFileName);

        JOptionPane.showMessageDialog(this, "UnKnownWords wrote successfully");
    }

    private void btnYesActionPerformed(ActionEvent e) {
        Color color =
                JColorChooser.showDialog(this, "Choose Color", Color.BLUE);

        fileProcess.changeColorOfUnKnownWords(color2HexString(color));

        JOptionPane.showMessageDialog(this,
                "UnKnownWords' color changed successfully");
    }

    private String color2HexString(Color color) {
        return "#" + Integer.toHexString(color.getRGB() & 0x00ffffff);
    }

    private void btnNoActionPerformed(ActionEvent e) {
        System.exit(0);
    }
}