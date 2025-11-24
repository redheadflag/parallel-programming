package labs5;


import utils.Logger;

public class CreateTicketConsumer extends Thread {

    private final EventQueue eventQueue;
    private final TicketStorage storage;
    public volatile boolean shouldRun = true;

    public CreateTicketConsumer(EventQueue eventQueue, TicketStorage storage) {
        this.eventQueue = eventQueue;
        this.storage = storage;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("CreateTicketConsumer");

        while (shouldRun) {
            Event eventToHandle = eventQueue.getEventIfType(EventType.CreateTicket);
            if (eventToHandle == null) {
                continue;
            }

            if (storage.containsTicket(eventToHandle.getTicketId())) {
                Logger.warn("Ticket already exists!");
                continue;
            }

            storage.insertTicket(eventToHandle.getTicketId());
            Logger.info("Ticket " + eventToHandle.getTicketId() + " created!");
        }
    }
}
