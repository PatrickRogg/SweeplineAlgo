package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.event.Event;

import java.util.PriorityQueue;

@Getter
@AllArgsConstructor
public class EventQueue {
    private final PriorityQueue<Event> eventQueue;

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public Event poll() {
        return eventQueue.poll();
    }

    public void offer(final Event event) {
        eventQueue.offer(event);
    }
}
