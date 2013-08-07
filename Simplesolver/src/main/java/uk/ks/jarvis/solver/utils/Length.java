package uk.ks.jarvis.solver.utils;

import uk.ks.jarvis.solver.beans.Point;

/**
 * Created by sayenko on 7/26/13.
 */
public class Length {

    public static double getLength(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
}
