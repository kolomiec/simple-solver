package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.StaticData;

import static uk.ks.jarvis.solver.utils.StaticData.getLengthBetweenTwoPoints;
import static uk.ks.jarvis.solver.utils.StaticData.setPoint;

/**
 * Created by sayenko on 7/17/13.
 */

public class Line implements Shape {
    private final String label1;
    private final String label2;
    public int color = 0;
    int numberTouchedPoint = 0;
    Point lastTouchCoordinates = new Point(0f, 0f);
    Point deltaTouchCoordinates = new Point(0f, 0f);
    private Point point1;
    private Point point2;
    private Point drawedPoint1 = new Point(0f, 0f);
    private Point drawedPoint2 = new Point(0f, 0f);


    public Line(Point point1, Point point2, String label1, String label2) {
        this.point1 = point1;
        this.point2 = point2;
        this.label1 = label1;
        this.label2 = label2;

        setPoint(drawedPoint1, point1);
        setPoint(drawedPoint2, point2);
        color = StaticData.getRandomColor();

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);

        canvas.drawLine(drawedPoint1.getX(), drawedPoint1.getY(), drawedPoint2.getX(), drawedPoint2.getY(), paint);

        Float pointRadius = 5.0f;
        canvas.drawCircle(drawedPoint1.getX(), drawedPoint1.getY(), pointRadius, paint);
        canvas.drawCircle(drawedPoint2.getX(), drawedPoint2.getY(), pointRadius, paint);

        StaticData.drawTextWithShadow(canvas, label1, drawedPoint1.getX() + pointRadius, drawedPoint1.getY() - pointRadius / 2);
        StaticData.drawTextWithShadow(canvas, label2, drawedPoint2.getX() + pointRadius, drawedPoint2.getY() - pointRadius / 2);
    }

    @Override
    public String toString() {
        return "x1:" + point1.getX() + ", y1:" + point1.getY() + "x2:" + point2.getX() + ", y2:" + point2.getY();
    }

    @Override
    public void move(Point point, boolean onlyMove) {
        if (!onlyMove) {
            changeLineCoordinates(point1, point2, point);
        } else {
            switch (numberTouchedPoint) {
                case 0: {
                    changeLineCoordinates(point1, point2, point);
                }
                break;
                case 1: {
                    changePointCoordinates(point1, point);
                }
                break;
                case 2: {
                    changePointCoordinates(point2, point);
                }
                break;
            }
        }
        setPoint(drawedPoint1, point1);
        setPoint(drawedPoint2, point2);
        setPoint(lastTouchCoordinates, point);
    }

    @Override
    public boolean isTouched(Point point) {
        setPoint(lastTouchCoordinates, point);
        if (isDotTouched(point1, point)) {
            numberTouchedPoint = 1;
            return true;
        } else if (isDotTouched(point2, point)) {
            numberTouchedPoint = 2;
            return true;
        } else {
            if (isLineTouched(point)) {
                numberTouchedPoint = 0;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Circle circle) {
        if (circle.isBorderTouched(this.getPoint1(), 20)) {
            Point newCoordinates = circle.getCoordinates(this.point1);
            switch (this.getNumberOfSelectedPoint()) {
                case 0:
                    Point deltaCoordinates = new Point(this.point1.getX() - this.point2.getX(), this.point1.getY() - this.point2.getY());
                    setPoint(drawedPoint1, newCoordinates);
                    setPoint(drawedPoint2, this.drawedPoint1.getX() - deltaCoordinates.getX(), this.drawedPoint1.getY() - deltaCoordinates.getY());
                    break;
                case 1:
                    setPoint(drawedPoint1, newCoordinates);
                    break;
                case 2:
                    break;
            }
            return true;
        }
        if (circle.isBorderTouched(this.getPoint2(), 20)) {
            Point newCoordinates = circle.getCoordinates(this.point2);
            switch (this.getNumberOfSelectedPoint()) {
                case 0:
                    Point deltaCoordinates = new Point(this.point1.getX() - this.point2.getX(), this.point1.getY() - this.point2.getY());
                    setPoint(drawedPoint2, newCoordinates);
                    setPoint(drawedPoint1, this.drawedPoint2.getX() + deltaCoordinates.getX(), this.drawedPoint2.getY() + deltaCoordinates.getY());
                    break;
                case 1:
                    break;
                case 2:
                    setPoint(drawedPoint1, newCoordinates);
                    break;
            }
            return true;
        } else {
            Point p = this.getCoordinates(circle.getCoordinatesOfCenterPoint());
            if (this.isLineTouched(p)) {
                double length = getLengthBetweenTwoPoints(p, circle.getCoordinatesOfCenterPoint());
                if (((length) < (circle.getRadius() + 15)) && ((length) > (circle.getRadius() - 15))) {
                    Point delta = circle.getCoordinates(p);

                    setPoint(delta, p.getX() - delta.getX(), p.getY() - delta.getY());
                    setPoint(drawedPoint1, this.drawedPoint1.getX() - delta.getX(), this.drawedPoint1.getY() - delta.getY());
                    setPoint(drawedPoint2, this.drawedPoint2.getX() - delta.getX(), this.drawedPoint2.getY() - delta.getY());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkTouchWithOtherFigure(Line line) {
        if (line.isLineTouched(this.getPoint1())) {

            Point newCoordinates = line.getCoordinates(this.getPoint1());
            switch (this.getNumberOfSelectedPoint()) {
                case 0:
                    Point deltaCoordinates = new Point(this.point1.getX() - this.point2.getX(), this.point1.getY() - this.point2.getY());
                    setPoint(drawedPoint1, newCoordinates);
                    setPoint(drawedPoint2, this.drawedPoint1.getX() - deltaCoordinates.getX(), this.drawedPoint1.getY() - deltaCoordinates.getY());
                    break;
                case 1:
                    setPoint(drawedPoint1, newCoordinates);
                    break;
                case 2:
                    break;
            }
            return true;
        }
        if (line.isLineTouched(this.getPoint2())) {
            Point newCoordinates = line.getCoordinates(this.getPoint2());
            switch (this.getNumberOfSelectedPoint()) {
                case 0:
                    Point deltaCoordinates = new Point(this.point1.getX() - this.point2.getX(), this.point1.getY() - this.point2.getY());
                    setPoint(drawedPoint2, newCoordinates);
                    setPoint(drawedPoint1, this.drawedPoint2.getX() + deltaCoordinates.getX(), this.drawedPoint2.getY() + deltaCoordinates.getY());
                    break;
                case 1:
                    break;
                case 2:
                    setPoint(drawedPoint2, newCoordinates);
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public void refreshCoordinates() {
        point1.setX(drawedPoint1.getX());
        point1.setY(drawedPoint1.getY());
        point2.setX(drawedPoint2.getX());
        point2.setY(drawedPoint2.getY());
    }

    public boolean isLineTouched(Point point) {
        return ((StaticData.getLengthBetweenTwoPoints(point1, point) + StaticData.getLengthBetweenTwoPoints(point2, point) - StaticData.getLengthBetweenTwoPoints(point1, point2)) < 3);
    }

    public boolean isDotTouched(Point p1, Point p2) {

        float delta = 20f;
        if ((p1.getX() < (p2.getX() + delta)) && (p1.getX() > (p2.getX() - delta)))
            if ((p1.getY() < (p2.getY() + delta)) && (p1.getY() > (p2.getY() - delta))) {
                return true;
            }
        return false;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getDrawedPoint1() {
        return drawedPoint1;
    }

    public Point getDrawedPoint2() {
        return drawedPoint2;
    }

    public int getNumberOfSelectedPoint() {
        return numberTouchedPoint;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    public void changePointCoordinates(Point point1, Point touchCoordinates) {
        deltaTouchCoordinates.setX(lastTouchCoordinates.getX() - touchCoordinates.getX());
        deltaTouchCoordinates.setY(lastTouchCoordinates.getY() - touchCoordinates.getY());

        setPoint(point1, point1.getX() - deltaTouchCoordinates.getX(), point1.getY() - deltaTouchCoordinates.getY());
    }

    public void changeLineCoordinates(Point point1, Point point2, Point touchCoordinates) {
        deltaTouchCoordinates.setX(lastTouchCoordinates.getX() - touchCoordinates.getX());
        deltaTouchCoordinates.setY(lastTouchCoordinates.getY() - touchCoordinates.getY());

        setPoint(point1, point1.getX() - deltaTouchCoordinates.getX(), point1.getY() - deltaTouchCoordinates.getY());
        setPoint(point2, point2.getX() - deltaTouchCoordinates.getX(), point2.getY() - deltaTouchCoordinates.getY());
    }

    public Point getCoordinates(Point point) {
        float firstLineLength, secondLineLength, bigLineLength;
        firstLineLength = (float) StaticData.getLengthBetweenTwoPoints(this.point1, point);
        secondLineLength = (float) StaticData.getLengthBetweenTwoPoints(this.point2, point);
        bigLineLength = (float) StaticData.getLengthBetweenTwoPoints(this.point1, this.point2);

        float ao = (float) ((Math.pow(firstLineLength, 2) - Math.pow(secondLineLength, 2) + Math.pow(bigLineLength, 2)) / (2 * bigLineLength));
        float coef = bigLineLength / ao;
        Point dotCoordinates = new Point(0f, 0f);
        dotCoordinates.setX(((point2.getX() - point1.getX()) / coef) + point1.getX());
        dotCoordinates.setY(((point2.getY() - point1.getY()) / coef) + point1.getY());
        return dotCoordinates;
    }
}