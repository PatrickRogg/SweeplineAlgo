package model.event;

import lombok.Getter;
import model.EventQueue;
import model.Line;
import model.SweepLine;

import java.util.HashSet;
import java.util.Set;

@Getter
public class IntersectionEvent extends Event {
    private final Line intersectionLine;

    public IntersectionEvent(final Line line, final Line intersectionLine) {
        super(line);
        this.intersectionLine = intersectionLine;
        this.setValue(calculateValue());
    }

    private double calculateValue() {
        if (this.getLine().isVertical()) {
            return this.getLine().getLeft().getX();
        } else if (this.getIntersectionLine().isVertical()) {
            return this.getIntersectionLine().getLeft().getX();
        } else if (this.getIntersectionLine().getSlope().equals(this.getLine().getSlope())) {
            return Math.max(this.getLine().getLeft().getX(), this.getIntersectionLine().getLeft().getX());
        }

        return (this.getLine().getYIntercept() - intersectionLine.getYIntercept()) /
                (intersectionLine.getSlope() - this.getLine().getSlope());
    }

    @Override
    public void handle(final EventQueue eventQueue, final SweepLine sweepLine) {
        sweepLine.swap(this.getLine(), this.getIntersectionLine());
        final int indexLine = sweepLine.getLineIndex(this.getLine());
        final int indexInterSectionLine = sweepLine.getLineIndex(this.getIntersectionLine());

        if (indexLine > indexInterSectionLine) {
            final Line lowerLine = sweepLine.lower(this.getIntersectionLine());
            final Line higherLine = sweepLine.higher(this.getLine());
            checkForIntersection(this.getIntersectionLine(), lowerLine, eventQueue);
            checkForIntersection(this.getLine(), higherLine, eventQueue);
        } else {
            final Line lowerLine = sweepLine.lower(this.getLine());
            final Line higherLine = sweepLine.higher(this.getIntersectionLine());
            checkForIntersection(this.getLine(), lowerLine, eventQueue);
            checkForIntersection(this.getIntersectionLine(), higherLine, eventQueue);
        }
    }

    public Set<String> getIntersections() {
        final Set<String> intersection = new HashSet<>();
        final String key1 = this.getLine().getId() + " " + this.getIntersectionLine().getId();
        final String key2 = this.getIntersectionLine().getId() + " " + this.getLine().getId();
        intersection.add(key1);
        intersection.add(key2);
        return intersection;
    }
}
