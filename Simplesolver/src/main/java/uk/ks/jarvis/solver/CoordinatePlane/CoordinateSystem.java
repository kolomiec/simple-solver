package uk.ks.jarvis.solver.CoordinatePlane;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;

/**
 * Created by sayenko on 8/10/13.
 */
public class CoordinateSystem {
    private final int labelStep = 30;
    private final int labelHeight = 5;
    private final int lineWidth = 1;
    private final int labelWidth = 2;
    private final int textWidth = 15;
    private Point startPointXAxis = new Point(20f, SystemInformation.DISPLAY_HEIGHT - 70f);
    private Point endPointXAxis = new Point(SystemInformation.DISPLAY_WIDTH - 5f, SystemInformation.DISPLAY_HEIGHT - 70f);
    private Point startPointYAxis = new Point(20f, (SystemInformation.DISPLAY_HEIGHT) - 70f);
    private Point endPointYAxis = new Point(20f, 5f);
    private Point originPoint = new Point(startPointXAxis);
    private Paint paint;

    public CoordinateSystem() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
//        startPointXAxis = new Point(50f, SystemInformation.DISPLAY_HEIGHT - 200f);
//        endPointXAxis = new Point(SystemInformation.DISPLAY_WIDTH - 10f, SystemInformation.DISPLAY_HEIGHT - 200f);
//        startPointYAxis = new Point(50f, (SystemInformation.DISPLAY_HEIGHT) - 200f);
//        originPoint = new Point(startPointXAxis);
    }

    public void draw(Canvas canvas) {
        drawXAxis(canvas);
        drawYAxis(canvas);
    }

    private void drawYAxis(Canvas canvas) {
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(startPointYAxis.getX(), startPointYAxis.getY(), endPointYAxis.getX(), endPointYAxis.getY(), paint);
        drawLabelOnYAxis(canvas);
    }

    private void drawLabelOnYAxis(Canvas canvas) {
        int labelsCount = (int) ((startPointYAxis.getY()) / labelStep);
        SystemInformation.COUNT_LABEL_BY_Y_AXIS = labelsCount;
        int magicNumber = 7; // :) ToDo Rename
        Point cursorPos = new Point(startPointYAxis.getX(), startPointXAxis.getY() - labelStep);
        paint.setStrokeWidth(labelWidth);
        paint.setTextSize(textWidth);
        for (int i = 0; i < labelsCount - 1; i++) {
            canvas.drawLine(cursorPos.getX() - labelHeight, cursorPos.getY(), cursorPos.getX() + labelHeight, cursorPos.getY(), paint);
            canvas.drawText(Integer.toString(i + 1), cursorPos.getX() - labelHeight - textWidth, cursorPos.getY() + magicNumber, paint);
            cursorPos.setY(cursorPos.getY() - labelStep);
        }
    }

    private void drawXAxis(Canvas canvas) {
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(startPointXAxis.getX(), startPointXAxis.getY(), endPointXAxis.getX(), endPointXAxis.getY(), paint);
        drawLabelOnXAxis(canvas);
    }

    private void drawLabelOnXAxis(Canvas canvas) {
        int labelsCount = (int) ((SystemInformation.DISPLAY_WIDTH - startPointXAxis.getX()) / labelStep);
        SystemInformation.COUNT_LABEL_BY_X_AXIS = labelsCount;
        int magicNumber = 7; // :) ToDo Rename
        Point cursorPos = new Point(startPointXAxis.getX() + labelStep, startPointXAxis.getY());
        paint.setStrokeWidth(labelWidth);
        paint.setTextSize(textWidth);
        for (int i = 0; i < labelsCount - 1; i++) {
            canvas.drawLine(cursorPos.getX(), cursorPos.getY() - labelHeight, cursorPos.getX(), cursorPos.getY() + labelHeight, paint);
            canvas.drawText(Integer.toString(i + 1), cursorPos.getX() - magicNumber, cursorPos.getY() + labelHeight + textWidth, paint);
            cursorPos.setX(cursorPos.getX() + labelStep);
        }
    }

    public Point getOriginPoint() {
        return originPoint;
    }

    public int getLabelStep() {
        return this.labelStep;
    }

}
