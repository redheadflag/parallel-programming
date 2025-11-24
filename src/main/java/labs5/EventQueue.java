package labs5;


import utils.Logger;

import java.util.LinkedList;

public class EventQueue {
    private final LinkedList<Event> queue = new LinkedList<>();

    public synchronized void addEvent(Event e) {
        queue.add(e);
        Logger.debug("New " + e.getType() + " event added to queue (" + queue.size() + ") ");
    }

    public synchronized int getSize() {
        return queue.size();
    }

    public synchronized Event getEventIfType(EventType type) {
        if (queue.isEmpty()) {
            return null;
        }

        if (queue.getFirst().getType() != type) {
            return null;
        }

        Event event = queue.pop();
        Logger.debug("Popped event of type " + type + ". Tasks left: " + queue.size());
        return event;
    }

}
