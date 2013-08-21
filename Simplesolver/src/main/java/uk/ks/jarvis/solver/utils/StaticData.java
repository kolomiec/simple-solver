package uk.ks.jarvis.solver.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.shapes.Circle;
import uk.ks.jarvis.solver.shapes.Dot;
import uk.ks.jarvis.solver.shapes.Line;
import uk.ks.jarvis.solver.shapes.Shape;
import uk.ks.jarvis.solver.shapes.ShapeList;

/**
 * Created with IntelliJ IDEA.
 * User: ksk
 * Date: 17.03.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class StaticData {
    public static boolean isCopiedFigure = false;
    private static ShapeList shapeList;

    public static void copyFigure(ShapeList shapes) {
        shapeList = new ShapeList();
        for (Shape shape : shapes.getShapeArray()) {
            if (((shape.getClass()) == (Line.class))) {
                Shape line = new Line(new Point(((Line) shape).getPoint1()), new Point(((Line) shape).getPoint2()), UpCaseLettersGenerator.getInstance().getNextName(),
                        UpCaseLettersGenerator.getInstance().getNextName());
                shapeList.getShapeArray().add(line);
            } else if (((shape.getClass()) == (Circle.class))) {
                Shape circle = new Circle((float) ((Circle) shape).getRadius(), new Point(((Circle) shape).getDrawedCenterPoint()), UpCaseLettersGenerator.getInstance().getNextName());
                shapeList.getShapeArray().add(circle);
            } else if (((shape.getClass()) == (Dot.class))) {
                Shape dot = new Dot(new Point(((Dot) shape).getCoordinatesPoint()), UpCaseLettersGenerator.getInstance().getNextName());
                shapeList.getShapeArray().add(dot);
            }
        }
        isCopiedFigure = true;
    }

    public static ShapeList getCopiedFigure() {
        if (isCopiedFigure) {
            copyFigure(shapeList);
            return shapeList;
        }
        return null;
    }

    public static Paint getLabelPaint(int color) {
        Paint labelPaint = new Paint();
        labelPaint.setColor(color);
        labelPaint.setTextSize(35.0f);
        return labelPaint;
    }

    public static int getRandomColor() {
        Random rand = new Random();
        return Color.argb(250, rand.nextInt(156) + 50, rand.nextInt(156) + 50, rand.nextInt(156) + 50);
    }

    public static double getLengthBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }

    public static void drawTextWithShadow(Canvas canvas, String label, float x, float y) {
        canvas.drawText(label, x + 1, y + 2, StaticData.getLabelPaint(Color.argb(100, 0, 0, 0)));
        canvas.drawText(label, x, y, StaticData.getLabelPaint(ColorTheme.LIGHT_COLOR));
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
