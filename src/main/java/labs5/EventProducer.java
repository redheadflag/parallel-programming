package labs5;

import java.util.Random;

public class EventProducer extends Thread {

    private final EventQueue queue;
    private final int eventsToProduce;

    public EventProducer(EventQueue queue,  int eventsToProduce) {
        this.queue = queue;
        this.eventsToProduce = eventsToProduce;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("EventProducer");

        Random r = new Random();
        EventType[] possibleEventTypes = EventType.values();

        for (int i = 0; i < eventsToProduce; i++) {

            int randomTypeIndex = r.nextInt(possibleEventTypes.length);
            EventType randomType = possibleEventTypes[randomTypeIndex];

            int ticketId = r.nextInt(100);

            queue.addEvent(new Event(ticketId, randomType));
        }
    }
}
