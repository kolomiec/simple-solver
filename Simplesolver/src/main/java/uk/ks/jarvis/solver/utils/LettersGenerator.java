package uk.ks.jarvis.solver.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ksk
 * Date: 17.03.13
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class LettersGenerator {
    private static LettersGenerator instance = new LettersGenerator();
    private List<String> englishLowCaseLetters = new ArrayList<String>();
    private List<String> englishUpperCaseLetters = new ArrayList<String>();
    private int digit = 0;
    private int nextIndexLetter = 0;

    private LettersGenerator() {
        fillLowCaseEnglishLettersList();
        fillUpperCaseEnglishLettersList();
    }

    public synchronized static LettersGenerator getInstance() {
        return instance;
    }

    public String getNextUpperCaseName() {
        String nextName = getNextUpperCaseLetter();
        if (digit > 0) {
            return nextName.concat(Integer.toString(digit));
        }
        return nextName;
    }

    public String getNextLowCaseName() {
        String nextName = getNextLowCaseLetter();
        if (digit > 0) {
            return nextName.concat(Integer.toString(digit));
        }
        return nextName;
    }

    private void fillLowCaseEnglishLettersList() {
        for (int i = 97; i <= 122; i++) {
            englishLowCaseLetters.add(new String(Character.toChars(i)));
        }
    }

    private void fillUpperCaseEnglishLettersList() {
        for (int i = 65; i <= 90; i++) {//97-122
            englishUpperCaseLetters.add(new String(Character.toChars(i)));
        }
    }

    private String getNextUpperCaseLetter() {
        if (nextIndexLetter < englishUpperCaseLetters.size()) {
            return englishUpperCaseLetters.get(nextIndexLetter++);
        } else {
            nextIndexLetter = 0;
            digit++;
            return englishUpperCaseLetters.get(nextIndexLetter++);
        }
    }

    private String getNextLowCaseLetter() {
        if (nextIndexLetter < englishLowCaseLetters.size()) {
            return englishLowCaseLetters.get(nextIndexLetter++);
        } else {
            nextIndexLetter = 0;
            digit++;
            return englishLowCaseLetters.get(nextIndexLetter++);
        }
    }
}