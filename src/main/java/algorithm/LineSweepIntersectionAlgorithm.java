package algorithm;

import model.EventQueue;
import model.Line;
import model.SweepLine;
import model.event.EndEvent;
import model.event.Event;
import model.event.IntersectionEvent;
import model.event.StartEvent;

import java.util.*;

public class LineSweepIntersectionAlgorithm implements IntersectionAlgorithm {
    public int getIntersectionCountOf(final List<Line> lines) {
        final EventQueue eventQueue = getEventQueue(lines);
        final SweepLine sweepLine = new SweepLine();
        final Set<String> seen = new HashSet<>();
        int intersections = 0;

        while (!eventQueue.isEmpty()) {
            final Event event = eventQueue.poll();

            if (event instanceof IntersectionEvent) {
                final int line1Id = event.getLine().getId();
                final int line2Id = ((IntersectionEvent) event).getIntersectionLine().getId();
                final String key1 = getKey(line1Id, line2Id);
                final String key2 = getKey(line2Id, line1Id);
                if (seen.add(key1) && seen.add(key2)) {
                    intersections++;
                } else {
                    continue;
                }
            }
            event.handle(eventQueue, sweepLine);
        }
        return intersections;
    }

    private String getKey(final int id1, final int id2) {
        return id1 + " " + id2;
    }

    private EventQueue getEventQueue(final List<Line> lines) {
        final PriorityQueue<Event> eventQueue = new PriorityQueue<>();

        for (final Line line : lines) {
            final StartEvent startEvent = new StartEvent(line);
            final EndEvent endEvent = new EndEvent(line);
            eventQueue.add(startEvent);
            eventQueue.add(endEvent);
        }

        return new EventQueue(eventQueue);
    }
}