package com.tewhem.learnEnglish;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.IndexWordSet;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

public class DictionaryProcess {

    private static Dictionary dictionary;

    private DictionaryProcess() {
    }

    public static void initialize()
            throws JWNLException, FileNotFoundException {
        JWNL.initialize(
                new FileInputStream("src\\main\\resources\\Properties.xml"));
        dictionary = Dictionary.getInstance();
    }

    public static String translateWord(String word) throws JWNLException {

        StringBuilder result = new StringBuilder();
        IndexWordSet indexWordSet = dictionary.lookupAllIndexWords(word);
        IndexWord[] indexWords = indexWordSet.getIndexWordArray();
        String line = "-----------------------------------";
        result.append(line);
        result.append("\n");
        result.append("Word: ");
        result.append(word);
        result.append("\n");
        result.append(line);
        result.append("\n");
        result.append("Mean: ");
        result.append("\n");
        for (IndexWord indexWord : indexWords) {
            Synset[] senses = indexWord.getSenses();

            result.append("\t");
            result.append(indexWord.getPOS());
            result.append(": ");
            result.append(senses[0].getGloss());
            result.append("\n");
        }
        result.append(line);
        result.append("\n");

        return result.toString();
    }
}