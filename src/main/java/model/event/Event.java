package model.event;

import lombok.Getter;
import lombok.Setter;
import model.EventQueue;
import model.Line;
import model.SweepLine;

import static algorithm.IntersectionHelper.doIntersect;

@Getter
@Setter
public abstract class Event implements Comparable<Event> {
    private final Line line;
    private double value;

    public Event(final Line line, final double value) {
        this.line = line;
        this.value = value;
    }

    public Event(final Line line) {
        this.line = line;
    }

    public abstract void handle(final EventQueue eventQueue, final SweepLine sweepLine);

    void checkForIntersection(final Line line1, final Line line2, final EventQueue eventQueue) {
        if (line1 != null && line2 != null && line1 != line2 && doIntersect(line1, line2)) {
            final IntersectionEvent event = new IntersectionEvent(line1, line2);
            eventQueue.offer(event);
        }
    }

    @Override
    public int compareTo(final Event that) {
        final int compare = Double.compare(this.value, that.getValue());
        if (compare == 0) {
            if (this instanceof StartEvent) {
                return -1;
            } else if (that instanceof StartEvent) {
                return 1;
            } else if (this instanceof EndEvent) {
                return 1;
            } else if (that instanceof EndEvent) {
                return -1;
            }

            return 0;
        }

        return compare;
    }
}
