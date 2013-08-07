package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.RandomColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sayenko on 8/3/13.
 */
public class ShapeList {
    List<Shape> shapeList = new ArrayList<Shape>();
    private int color = 0;

    public ShapeList(Shape shape) {
        shapeList.add(shape);
        color = RandomColor.getRandomColor();
    }

    public void setRandomColor() {
        if (shapeList.size() == 1){
            shapeList.get(0).setColor(RandomColor.getRandomColor());
        } else
        color = RandomColor.getRandomColor();
    }

    public boolean checkTouch(Shape shape1, Shape shape2) {
        if (((shape1.getClass()) == (Line.class)) && ((shape2.getClass()) == (Circle.class))) {
            return shape1.checkTouchWithOtherFigure((Circle) shape2);

        } else if (((shape1.getClass()) == (Line.class)) && ((shape2.getClass()) == (Line.class))) {
            return shape1.checkTouchWithOtherFigure((Line) shape2);

        } else if (((shape1.getClass()) == (Circle.class)) && ((shape2.getClass()) == (Circle.class))) {
            return shape1.checkTouchWithOtherFigure((Circle) shape2);

        } else if (((shape1.getClass()) == (Circle.class)) && ((shape2.getClass()) == (Line.class))) {
            return shape1.checkTouchWithOtherFigure((Line) shape2);
        }
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (shapeList.size() == 1){
            paint.setColor(shapeList.get(0).getColor());
        } else
        paint.setColor(color);
        for (Shape shape : shapeList) {
            shape.draw(canvas, paint);
        }
    }

    public void move(Point point) {
        boolean manyFigures = true;
        if (shapeList.size() > 1) {
            manyFigures = false;
        }
        for (Shape shape : shapeList) {
            shape.move(point, manyFigures);
        }
    }

    public boolean isTouched(Point point) {
        boolean isTouch = false;
        for (Shape shape : shapeList) {
            if (shape.isTouched(point)) {
                isTouch = true;
            }
        }
        return isTouch;
    }

    public Shape getTouchedFigureInList(Point point) {
        for (Shape shape : shapeList) {
            if (shape.isTouched(point)) {
                return shape;
            }
        }
        return null;
    }

    public boolean checkTouchWithOtherFigure(ShapeList shapeList) {
        int count = 0;
        for (Shape shape1 : this.shapeList) {
            for (Shape shape2 : shapeList.getShapeArray()) {
                if (checkTouch(shape1, shape2)) {
//                    changeCoordinates(count);
                    return true;
                }
            }
            count++;
        }
        return false;
    }

    private void changeCoordinates(int count) {
        Point delta = new Point(0f, 0f);
        if (shapeList.get(count).getClass() == (Line.class)) {
            delta.setX(((Line) shapeList.get(count)).getPoint1().getX() - ((Line) shapeList.get(count)).getDrawedPoint1().getX());
            delta.setY(((Line) shapeList.get(count)).getPoint1().getY() - ((Line) shapeList.get(count)).getDrawedPoint1().getY());
        } else if (shapeList.get(count).getClass() == (Circle.class)) {
            delta.setX(((Circle) shapeList.get(count)).getCenterPoint().getX() - ((Circle) shapeList.get(count)).getDrawedCenterPoint().getX());
            delta.setY(((Circle) shapeList.get(count)).getCenterPoint().getY() - ((Circle) shapeList.get(count)).getDrawedCenterPoint().getY());
        }
        int count1 = 0;
        for (Shape shape : this.shapeList) {
            if (count1 != count) {
                if (shape.getClass() == (Line.class)) {
                    ((Line) shape).getDrawedPoint1().setX(((Line) shape).getDrawedPoint1().getX()-delta.getX());
                    ((Line) shape).getDrawedPoint1().setY(((Line) shape).getDrawedPoint1().getY()-delta.getY());
                    ((Line) shape).getDrawedPoint2().setX(((Line) shape).getDrawedPoint2().getX()-delta.getX());
                    ((Line) shape).getDrawedPoint2().setY(((Line) shape).getDrawedPoint2().getY()-delta.getY());
                } else if (shape.getClass() == (Circle.class)) {
                    ((Circle) shape).getDrawedCenterPoint().setX(((Circle) shape).getDrawedCenterPoint().getX()-delta.getX());
                    ((Circle) shape).getDrawedCenterPoint().setY(((Circle) shape).getDrawedCenterPoint().getY()-delta.getY());
                }
            }
            count++;
        }
    }

    public void refreshCoordinates() {
        for (Shape shape : shapeList) {
            shape.refreshCoordinates();
        }
    }

    public List<Shape> getShapeArray() {
        return shapeList;
    }
}
