package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.utils.StaticData;

/**
 * Created by sayenko on 8/3/13.
 */
public class ShapeList {
    List<Shape> shapeList = new ArrayList<Shape>();
    private int color = 0;

    public ShapeList() {
        color = StaticData.getRandomColor();
    }

    public ShapeList(Shape shape) {
        shapeList.add(shape);
        color = StaticData.getRandomColor();
    }

    public void setRandomColor() {
        if (shapeList.size() == 1) {
            shapeList.get(0).setColor(StaticData.getRandomColor());
        } else
            color = StaticData.getRandomColor();
    }

    public Point checkTouch(Shape shape1, Shape shape2) {
        if (((shape1.getClass()) == (ShortLine.class)) && ((shape2.getClass()) == (Circle.class))) {
            return shape1.checkTouchWithOtherFigure((Circle) shape2);

        } else if (((shape1.getClass()) == (ShortLine.class)) && ((shape2.getClass()) == (ShortLine.class))) {
            return shape1.checkTouchWithOtherFigure((ShortLine) shape2);

        } else if (((shape1.getClass()) == (Circle.class)) && ((shape2.getClass()) == (Circle.class))) {
            return shape1.checkTouchWithOtherFigure((Circle) shape2);

        } else if (((shape1.getClass()) == (Circle.class)) && ((shape2.getClass()) == (ShortLine.class))) {
            return shape1.checkTouchWithOtherFigure((ShortLine) shape2);
        }
        return null;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (shapeList.size() == 1) {
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
        for (Shape shape1 : this.shapeList) {
            for (Shape shape2 : shapeList.getShapeArray()) {
                Point delta = checkTouch(shape1, shape2);
                if (delta != null) {
                    changeCoordinatesToDelta(delta);
                    return true;
                }
            }
        }
        return false;
    }

    public void setFigureThatItWillNotBeOutsideTheScreen(float maxX, float maxY) {
        for (Shape shape : this.shapeList) {
            Point deltaPoint = shape.setFigureThatItWillNotBeOutsideTheScreen(maxX, maxY);
            if (deltaPoint != null) {
                for (Shape shape1 : this.shapeList) {
                    if (shape1.getClass() == ShortLine.class) {
                        ((ShortLine) shape1).numberTouchedPoint = 0;
                    }
                }
                changeCoordinatesToDelta(deltaPoint);
            }
        }
    }

    public void changeCoordinatesToDelta(Point delta) {
        for (Shape shape1 : this.shapeList) {
            shape1.changeCoordinatesToDelta(delta);
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

    @Override
    public String toString() {
        return shapeList.get(0).toString();
    }
}
