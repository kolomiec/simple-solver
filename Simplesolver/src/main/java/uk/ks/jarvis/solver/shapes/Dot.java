package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.RandomColor;
import uk.ks.jarvis.solver.utils.StaticData;

/**
 * Created by sayenko on 7/14/13.
 */
public class Dot implements Shape {

    private final Float radius = 5.0f;
    private final Point point;
    private final String label;
    Point lastTouchCoordinates = new Point(0f, 0f);
    Point deltaTouchCoordinates = new Point(0f, 0f);
    private Point delta = new Point(15f, 15f);
    public int color = 0;


    public Dot(Point point, String label) {
        this.point = point;
        this.label = label;
        color = RandomColor.getRandomColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(point.getX(), point.getY(), radius, paint);

        canvas.drawText(label, (point.getX() + radius) + 1, (point.getY() - radius / 2) + 2, StaticData.getLabelPaint(Color.BLACK));
        canvas.drawText(label, point.getX() + radius, point.getY() - radius / 2, StaticData.getLabelPaint(Color.WHITE));
    }

    @Override
    public void move(Point touchCoordinates, boolean b) {
        deltaTouchCoordinates.setX(lastTouchCoordinates.getX() - touchCoordinates.getX());
        deltaTouchCoordinates.setY(lastTouchCoordinates.getY() - touchCoordinates.getY());
        this.point.setX(this.point.getX() - deltaTouchCoordinates.getX());
        this.point.setY(this.point.getY() - deltaTouchCoordinates.getY());

        lastTouchCoordinates.setX(touchCoordinates.getX());
        lastTouchCoordinates.setY(touchCoordinates.getY());
    }
    public boolean isTouched(Point point) {
        boolean betweenDelta = (this.point.getX() < (point.getX() + delta.getX())) && (this.point.getX() > (point.getX() - delta.getX()));
        if (betweenDelta) {
            boolean betweenDelta2 = (this.point.getY() < (point.getY() + delta.getY())) && (this.point.getY() > (point.getY() - delta.getY()));
            if (betweenDelta2) {
                lastTouchCoordinates.setX(point.getX());
                lastTouchCoordinates.setY(point.getY());
                return true;
            }
        }
        return false;
    }


    @Override
    public int getColor() {
        return color;
    }
    @Override
    public void setColor(int color) {
        this.color=color;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Circle circle) {
        return false;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Line line) {
        return false;
    }
    public Point getPoint() {
        return point;
    }

    @Override
    public void refreshCoordinates() {

    }
}
