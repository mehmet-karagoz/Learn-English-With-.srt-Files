package com.tewhem.learnEnglish;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import net.didion.jwnl.JWNLException;

public class FileProcess implements Serializable {

    private static final long serialVersionUID = 001L;
    private final File srtFile;
    private Set<String> unKnownWords = new HashSet<>();
    private Set<String> knownWords = new HashSet<>();

    public FileProcess(File srtFile) {
        this.srtFile = srtFile;

        try (var reader = new Scanner(
                new BufferedReader(new FileReader("B1-Words.txt")))) {

            while (reader.hasNext()) {
                knownWords.add(reader.next().toLowerCase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readSrtFile() {
        try (var reader =
                new Scanner(new BufferedReader(new FileReader(srtFile)))) {

            while (reader.hasNext()) {
                String word = reader.next().toLowerCase();

                if (word.matches("[a-zA-Z]+") && !knownWords.contains(word)) {
                    unKnownWords.add(word);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUnKnownWords(String fileName) {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(fileName))) {
            DictionaryProcess.initialize();
            for (String word : unKnownWords) {
                writer.write(DictionaryProcess.translateWord(word));
            }

        } catch (IOException | JWNLException e) {
            e.printStackTrace();
        }

    }

    public File getSrtFile() {
        return srtFile;
    }

    public Set<String> getWords() {
        return unKnownWords;
    }

    public void setWords(Set<String> words) {
        this.unKnownWords = words;
    }

    public Set<String> getKnownWords() {
        return knownWords;
    }

    public void setKnownWords(Set<String> knownWords) {
        this.knownWords = knownWords;
    }

}