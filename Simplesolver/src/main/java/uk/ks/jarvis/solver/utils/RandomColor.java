package uk.ks.jarvis.solver.utils;

import android.graphics.Color;

import java.util.Random;

public class RandomColor {

    public static int getRandomColor() {
        Random rand = new Random();
        return Color.argb(250, rand.nextInt(156) + 100, rand.nextInt(156) + 100, rand.nextInt(156) + 100);
    }
}