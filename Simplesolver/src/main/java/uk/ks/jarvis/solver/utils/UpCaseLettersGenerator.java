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
public class UpCaseLettersGenerator {
    private static UpCaseLettersGenerator instance = new UpCaseLettersGenerator();
    private List<String> englishLetters = new ArrayList<String>();
    private int digit = 0;
    private int nextIndexLetter = 0;

    private UpCaseLettersGenerator() {
        fillEnglishLettersList();
    }

    public synchronized static UpCaseLettersGenerator getInstance() {
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
        for (int i = 65; i <= 90; i++) {//97-122
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