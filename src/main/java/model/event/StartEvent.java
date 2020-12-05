package model.event;

import model.EventQueue;
import model.Line;
import model.SweepLine;

import java.util.List;

public class StartEvent extends Event {
    public StartEvent(final Line line) {
        super(line, line.getLeft().getX());
    }

    @Override
    public void handle(final EventQueue eventQueue, final SweepLine sweepLine) {
        sweepLine.add(this.getLine());
        final List<Line> lowerLines = sweepLine.lowerLines(this.getLine());
        final List<Line> higherLines = sweepLine.higherLines(this.getLine());

        for (final Line lowerLine : lowerLines) {
            checkForIntersection(lowerLine, this.getLine(), eventQueue);
        }

        for (final Line higherLine : higherLines) {
            checkForIntersection(this.getLine(), higherLine, eventQueue);
        }
    }

}
