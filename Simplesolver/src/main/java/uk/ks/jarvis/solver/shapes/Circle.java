package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.RandomColor;
import uk.ks.jarvis.solver.utils.StaticData;

import static uk.ks.jarvis.solver.utils.Length.getLength;


/**
 * Created by sayenko on 7/26/13.
 */
public class Circle implements Shape {

    private final String label;
    Point lastTouchCoordinates = new Point(0f, 0f);
    Point deltaTouchCoordinates = new Point(0f, 0f);
    private Point point;
    private Point drawedCenterPoint = new Point(0f, 0f);
    private double radius;
    private boolean radiusChangeMode;
    public int color = 0;



    public Circle(Float radius, Point point, String label) {
        this.point = point;
        this.label = label;
        drawedCenterPoint.setX(this.point.getX());
        drawedCenterPoint.setY(this.point.getY());
        color = RandomColor.getRandomColor();
    }

    public static Point getCoordinatesOfBorderOfCircle(Point point, Point point2, double radius) {
        float radius2 = (float) getLength(point2, point);

        float ratioOfTheRadii = ((float) radius / radius2);

        Point dotCoordinates = new Point(0f, 0f);
        dotCoordinates.setX(((point.getX() - point2.getX()) * ratioOfTheRadii) + point2.getX());
        dotCoordinates.setY(((point.getY() - point2.getY()) * ratioOfTheRadii) + point2.getY());

        return dotCoordinates;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(drawedCenterPoint.getX(), drawedCenterPoint.getY(), (float) radius, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(drawedCenterPoint.getX(), drawedCenterPoint.getY(), 2, paint);

        canvas.drawText(label, (drawedCenterPoint.getX()) + 5, (drawedCenterPoint.getY()) + 2, StaticData.getLabelPaint(Color.BLACK));
        canvas.drawText(label, drawedCenterPoint.getX() + 4, drawedCenterPoint.getY(), StaticData.getLabelPaint(Color.WHITE));
    }

    @Override
    public void move(Point touchCoordinates, boolean b) {
        if (radiusChangeMode && b) {
            changeRadius(touchCoordinates);
        } else {
            deltaTouchCoordinates.setX(lastTouchCoordinates.getX() - touchCoordinates.getX());
            deltaTouchCoordinates.setY(lastTouchCoordinates.getY() - touchCoordinates.getY());
            this.point.setX(this.point.getX() - deltaTouchCoordinates.getX());
            this.point.setY(this.point.getY() - deltaTouchCoordinates.getY());
        }
        drawedCenterPoint.setX(this.point.getX());
        drawedCenterPoint.setY(this.point.getY());

        lastTouchCoordinates.setX(touchCoordinates.getX());
        lastTouchCoordinates.setY(touchCoordinates.getY());
    }

    public Point getCenterPoint() {
        return point;
    }

    public double getRadius() {
        return radius;
    }

    public Point getDrawedCenterPoint() {
        return drawedCenterPoint;
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
    public boolean isTouched(Point point) {
        radiusChangeMode = false;
        lastTouchCoordinates.setX(point.getX());
        lastTouchCoordinates.setY(point.getY());

        if (isBorderTouched(point, 15)) {
            radiusChangeMode = true;
            return true;
        } else {
            double length = getLength(point, this.point);
            return (length < radius);
        }
    }

    @Override
    public boolean checkTouchWithOtherFigure(Circle circle) {
        double length1 = getLength(this.getCenterPoint(), circle.getCenterPoint());
        double length2 = this.getRadius() + circle.getRadius();

        if (((length1) < (length2 + 15)) && ((length1) > (length2 - 15))) {
            this.getCoordinates(circle.getCenterPoint());
            Point newCoordinates = getCoordinatesOfBorderOfCircle(this.getCenterPoint(), circle.getCenterPoint(), length2);
            this.getDrawedCenterPoint().setX(newCoordinates.getX());
            this.getDrawedCenterPoint().setY(newCoordinates.getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Line line) {
        Point p = line.getCoordinates(this.getCenterPoint());
        if (line.isLineTouched(p)) {
            double length = getLength(p, this.getCenterPoint());
            if (((length) < (this.getRadius() + 15)) && ((length) > (this.getRadius() - 15))) {
                Point delta = this.getCoordinates(p);
                delta.setX(p.getX() - delta.getX());
                delta.setY(p.getY() - delta.getY());

                this.getDrawedCenterPoint().setX(this.getCenterPoint().getX() + delta.getX());
                this.getDrawedCenterPoint().setY(this.getCenterPoint().getY() + delta.getY());
                return true;
            }
        }
        if (this.isBorderTouched(line.getPoint1(), 20)) {
            Point newCoordinates = getCoordinatesOfBorderOfCircle(this.getCenterPoint(), line.getPoint1(), this.getRadius());
            this.getDrawedCenterPoint().setX(newCoordinates.getX());
            this.getDrawedCenterPoint().setY(newCoordinates.getY());
            return true;
        }
        if (this.isBorderTouched(line.getPoint2(), 20)) {
            Point newCoordinates = getCoordinatesOfBorderOfCircle(this.getCenterPoint(), line.getPoint2(), this.getRadius());
            this.getDrawedCenterPoint().setX(newCoordinates.getX());
            this.getDrawedCenterPoint().setY(newCoordinates.getY());
            return true;
        }
        return false;
    }

    @Override
    public void refreshCoordinates() {
        point.setX(drawedCenterPoint.getX());
        point.setY(drawedCenterPoint.getY());
    }

    public boolean isBorderTouched(Point point, int deltaRadius) {
        double length = getLength(point, this.point);
        return (length < radius + deltaRadius) && (length > radius - deltaRadius);
    }

    public Point getCoordinates(Point point) {
        double radius2 = getLength(this.point, point);
        Float ratioOfTheRadii = (float) (radius / radius2);

        Point dotCoordinates = new Point(0f, 0f);
        dotCoordinates.setX(((point.getX() - this.point.getX()) * ratioOfTheRadii) + this.point.getX());
        dotCoordinates.setY(((point.getY() - this.point.getY()) * ratioOfTheRadii) + this.point.getY());

        return dotCoordinates;
    }

    public void changeRadius(Point point) {
        radius = getLength(this.point, point);
    }
}
