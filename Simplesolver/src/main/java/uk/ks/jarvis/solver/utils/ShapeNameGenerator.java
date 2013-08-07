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
public class ShapeNameGenerator {
    private static ShapeNameGenerator instance = new ShapeNameGenerator();
    private List<String> englishLetters = new ArrayList<String>();
    private int digit = 0;
    private int nextIndexLetter = 0;

    private ShapeNameGenerator() {
        fillEnglishLettersList();
    }

    public synchronized static ShapeNameGenerator getInstance() {
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
        for (int i = 65; i <= 90; i++) {
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