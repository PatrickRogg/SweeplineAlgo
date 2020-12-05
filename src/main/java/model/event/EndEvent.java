package model.event;

import model.EventQueue;
import model.Line;
import model.SweepLine;

public class EndEvent extends Event {
    public EndEvent(final Line line) {
        super(line, line.getRight().getX());
    }

    @Override
    public void handle(final EventQueue eventQueue, final SweepLine sweepLine) {
        final Line lowerLine = sweepLine.lower(this.getLine());
        final Line higherLine = sweepLine.higher(this.getLine());

        if (lowerLine != higherLine) {
            checkForIntersection(lowerLine, higherLine, eventQueue);
            sweepLine.remove(this.getLine());
        }
    }
}
