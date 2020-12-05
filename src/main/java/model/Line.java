package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Line implements Comparable<Line> {
    private final int id;
    private final Point left;
    private final Point right;
    private final Double slope;
    private final Double yIntercept;
    private double value;

    public Line(final int id, final Point point1, final Point point2) {
        this.id = id;
        if (point1.getX() < point2.getX() || (point1.getY() < point2.getY() && point1.getX() == point2.getX())) {
            this.left = point1;
            this.right = point2;
        } else {
            this.left = point2;
            this.right = point1;
        }

        if (isVertical()) {
            this.slope = null;
            this.yIntercept = null;
        } else {
            this.slope = calculateSlope();
            this.yIntercept = calculateYIntercept();
        }
        calculateValue(this.getLeft().getX());
    }

    private Double calculateSlope() {
        return (right.getY() - left.getY()) / (right.getX() - left.getX());
    }

    private double calculateYIntercept() {
        return this.left.getY() - slope * this.left.getX();
    }

    public void calculateValue(final double currentX) {
        if (this.slope == null || this.yIntercept == null) {
            this.value = this.left.getY();
        } else {
            this.value = slope * currentX + yIntercept;
        }
    }

    public boolean isVertical() {
        return this.getLeft().getX() == this.getRight().getX();
    }

    @Override
    public String toString() {
        return this.id + " " + this.value;
    }

    @Override
    public int compareTo(final Line that) {
        return Double.compare(this.getValue(), that.getValue());
    }
}
