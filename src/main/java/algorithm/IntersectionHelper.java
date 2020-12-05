package algorithm;

import model.Line;
import model.Point;

public class IntersectionHelper {
    private IntersectionHelper() {}

    public static boolean doIntersect(final Line line1, final Line line2) {
        final int line2StartPointOrientation = getOrientationOfLineToPoint(line1, line2.getLeft());
        final int line2EndPointOrientation = getOrientationOfLineToPoint(line1, line2.getRight());
        final int line1StartPointOrientation = getOrientationOfLineToPoint(line2, line1.getLeft());
        final int line1EndPointOrientation = getOrientationOfLineToPoint(line2, line1.getRight());

        if (line2StartPointOrientation != line2EndPointOrientation && line1StartPointOrientation != line1EndPointOrientation) {
            return true;
        } else if (line2StartPointOrientation == 0 && isPointOnLine(line2.getLeft(), line1)) {
            return true;
        } else if (line2EndPointOrientation == 0 && isPointOnLine(line2.getRight(), line1)) {
            return true;
        } else if (line1StartPointOrientation == 0 && isPointOnLine(line1.getLeft(), line2)) {
            return true;
        } else return line1EndPointOrientation == 0 && isPointOnLine(line1.getRight(), line2);
    }

    private static boolean isPointOnLine(final Point point, final Line line) {
        return ((
                (line.getRight().getX() <= point.getX() && point.getX() <= line.getLeft().getX()) ||
                        (line.getLeft().getX() <= point.getX() && point.getX() <= line.getRight().getX())
        ) && (
                (line.getRight().getY() <= point.getY() && point.getY() <= line.getLeft().getY()) ||
                        (line.getLeft().getY() <= point.getY() && point.getY() <= line.getRight().getY())
        ));
    }

    private static int getOrientationOfLineToPoint(final Line line, final Point point) {
        double orientation = 0.0;
        orientation += line.getLeft().getX() * line.getRight().getY() - line.getLeft().getY() * line.getRight().getX();
        orientation += line.getRight().getX() * point.getY() - line.getRight().getY() * point.getX();
        orientation += line.getLeft().getY() * point.getX() - line.getLeft().getX() * point.getY();

        if (orientation > 0.0) {
            return 1;
        } else if (orientation < 0.0) {
            return 2;
        } else {
            return 0;
        }
    }
}
