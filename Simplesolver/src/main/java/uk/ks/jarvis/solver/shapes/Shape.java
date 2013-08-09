package uk.ks.jarvis.solver.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import uk.ks.jarvis.solver.beans.Point;

/**
 * Created by sayenko on 7/14/13.
 */

public interface Shape {

    public void draw(Canvas c, Paint p);

    public void move(Point point, boolean onlyMove);

    public boolean isTouched(Point point);

    public boolean checkTouchWithOtherFigure(Circle circle);

    public boolean checkTouchWithOtherFigure(Line line);

    public void refreshCoordinates();

    public int getColor();

    public void setColor(int color);

}
