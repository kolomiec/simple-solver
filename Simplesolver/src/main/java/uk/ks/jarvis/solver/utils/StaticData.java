package uk.ks.jarvis.solver.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;

import java.util.Random;

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

    public static int getRandomColor() {
        Random rand = new Random();
        return Color.argb(250, rand.nextInt(156) + 100, rand.nextInt(156) + 100, rand.nextInt(156) + 100);
    }

    public static double getLengthBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }

    public static void drawTextWithShadow(Canvas canvas, String label, float x, float y) {
        canvas.drawText(label, x + 1, y + 2, StaticData.getLabelPaint(Color.BLACK));
        canvas.drawText(label, x, y, StaticData.getLabelPaint(Color.WHITE));
    }

    public static void setPoint(Point point, float x, float y) {
        point.setX(x);
        point.setY(y);
    }

    public static void setPoint(Point point1, Point point2) {
        point1.setX(point2.getX());
        point1.setY(point2.getY());
    }
}
