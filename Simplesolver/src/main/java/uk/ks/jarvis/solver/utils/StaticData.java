package uk.ks.jarvis.solver.utils;

import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 * User: ksk
 * Date: 17.03.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class StaticData {
    public static Paint getLabelPaint(int color) {
        Paint labelPaint = new Paint();
        labelPaint.setColor(color);
        labelPaint.setTextSize(35.0f);
        return labelPaint;
    }
}
