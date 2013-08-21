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
public class LowCaseLettersGenerator {
    private static LowCaseLettersGenerator instance = new LowCaseLettersGenerator();
    private List<String> englishLetters = new ArrayList<String>();
    private int digit = 0;
    private int nextIndexLetter = 0;

    private LowCaseLettersGenerator() {
        fillEnglishLettersList();
    }

    public synchronized static LowCaseLettersGenerator getInstance() {
        return instance;
    }

    public String getNextName() {
        String nextName = getNextLetters();
        if (digit > 0) {
            return nextName.concat(Integer.toString(digit));
        }
        return nextName;
    }

    private void fillEnglishLettersList() {
        for (int i = 97; i <= 122; i++) {
            englishLetters.add(new String(Character.toChars(i)));
        }
    }

    private String getNextLetters() {
        if (nextIndexLetter < englishLetters.size()) {
            return englishLetters.get(nextIndexLetter++);
        } else {
            nextIndexLetter = 0;
            digit++;
            return englishLetters.get(nextIndexLetter++);
        }
    }
}