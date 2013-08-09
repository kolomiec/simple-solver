package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.StaticData;

import static uk.ks.jarvis.solver.utils.StaticData.setPoint;


/**
 * Created by sayenko on 7/26/13.
 */
public class Circle implements Shape {

    private final String label;
    public int color = 0;
    Point lastTouchCoordinates = new Point(0f, 0f);
    Point deltaTouchCoordinates = new Point(0f, 0f);
    private Point centerPoint;
    private Point drawedCenterPoint = new Point(0f, 0f);
    private double radius;
    private boolean radiusChangeMode;


    public Circle(Float radius, Point point, String label) {
        this.radius = radius;
        this.centerPoint = point;
        this.label = label;
        setPoint(drawedCenterPoint, point);
        color = StaticData.getRandomColor();
    }

    public static Point getCoordinatesOfBorderOfCircle(Point point, Point point2, double radius) {
        float radius2 = (float) StaticData.getLengthBetweenTwoPoints(point2, point);

        float ratioOfTheRadii = ((float) radius / radius2);

        Point dotCoordinates = new Point(0f, 0f);
        dotCoordinates.setX(((point.getX() - point2.getX()) * ratioOfTheRadii) + point2.getX());
        dotCoordinates.setY(((point.getY() - point2.getY()) * ratioOfTheRadii) + point2.getY());

        return dotCoordinates;
    }

    @Override
    public String toString() {
        return "x:" + centerPoint.getX() + ", y:" + centerPoint.getY() + ", radius:" + radius;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(drawedCenterPoint.getX(), drawedCenterPoint.getY(), (float) radius, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(drawedCenterPoint.getX(), drawedCenterPoint.getY(), 2, paint);

        StaticData.drawTextWithShadow(canvas, label, drawedCenterPoint.getX() + 4, drawedCenterPoint.getY());
    }

    @Override
    public void move(Point touchCoordinates, boolean onlyMove) {
        if (radiusChangeMode && onlyMove) {
            changeRadius(touchCoordinates);
        } else {
            setPoint(deltaTouchCoordinates, lastTouchCoordinates.getX() - touchCoordinates.getX(), lastTouchCoordinates.getY() - touchCoordinates.getY());
            setPoint(centerPoint, centerPoint.getX() - deltaTouchCoordinates.getX(), centerPoint.getY() - deltaTouchCoordinates.getY());
        }
        setPoint(drawedCenterPoint, centerPoint);
        setPoint(lastTouchCoordinates, touchCoordinates);
    }

    public Point getCoordinatesOfCenterPoint() {
        return centerPoint;
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
        this.color = color;
    }

    @Override
    public boolean isTouched(Point point) {
        radiusChangeMode = false;
        setPoint(lastTouchCoordinates, point);

        if (isBorderTouched(point, 15)) {
            radiusChangeMode = true;
            return true;
        } else {
            double length = StaticData.getLengthBetweenTwoPoints(point, this.centerPoint);
            return (length < radius);
        }
    }

    @Override
    public boolean checkTouchWithOtherFigure(Circle circle) {
        double length1 = StaticData.getLengthBetweenTwoPoints(this.getCoordinatesOfCenterPoint(), circle.getCoordinatesOfCenterPoint());
        double length2 = this.getRadius() + circle.getRadius();

        if (((length1) < (length2 + 15)) && ((length1) > (length2 - 15))) {
            this.getCoordinates(circle.getCoordinatesOfCenterPoint());
            Point newCoordinates = getCoordinatesOfBorderOfCircle(centerPoint, circle.getCoordinatesOfCenterPoint(), length2);
            setPoint(drawedCenterPoint, newCoordinates);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Line line) {
        Point p = line.getCoordinates(this.getCoordinatesOfCenterPoint());
        if (line.isLineTouched(p)) {
            double length = StaticData.getLengthBetweenTwoPoints(p, this.getCoordinatesOfCenterPoint());
            if (((length) < (this.radius + 15)) && ((length) > (this.radius - 15))) {
                Point delta = this.getCoordinates(p);
                delta.setX(p.getX() - delta.getX());
                delta.setY(p.getY() - delta.getY());

                setPoint(drawedCenterPoint, centerPoint.getX() + delta.getX(), centerPoint.getY() + delta.getY());
                return true;
            }
        }
        if (this.isBorderTouched(line.getPoint1(), 20)) {
            Point newCoordinates = getCoordinatesOfBorderOfCircle(centerPoint, line.getPoint1(), radius);
            setPoint(drawedCenterPoint, newCoordinates);
            return true;
        }
        if (this.isBorderTouched(line.getPoint2(), 20)) {
            Point newCoordinates = getCoordinatesOfBorderOfCircle(centerPoint, line.getPoint2(), radius);
            setPoint(drawedCenterPoint, newCoordinates);
            return true;
        }
        return false;
    }

    @Override
    public void refreshCoordinates() {
        setPoint(centerPoint, drawedCenterPoint);
    }

    public boolean isBorderTouched(Point point, int deltaRadius) {
        double length = StaticData.getLengthBetweenTwoPoints(point, this.centerPoint);
        return (length < radius + deltaRadius) && (length > radius - deltaRadius);
    }

    public Point getCoordinates(Point point) {
        double radius2 = StaticData.getLengthBetweenTwoPoints(this.centerPoint, point);
        Float ratioOfTheRadii = (float) (radius / radius2);

        Point dotCoordinates = new Point(0f, 0f);
        dotCoordinates.setX(((point.getX() - this.centerPoint.getX()) * ratioOfTheRadii) + this.centerPoint.getX());
        dotCoordinates.setY(((point.getY() - this.centerPoint.getY()) * ratioOfTheRadii) + this.centerPoint.getY());

        return dotCoordinates;
    }

    public void changeRadius(Point point) {
        radius = StaticData.getLengthBetweenTwoPoints(this.centerPoint, point);
    }
}