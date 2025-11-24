package labs5;


import utils.Logger;

public class ValidateTicketConsumer extends Thread {

    private final EventQueue eventQueue;
    private final TicketStorage storage;
    public volatile boolean shouldRun = true;

    public ValidateTicketConsumer(EventQueue eventQueue, TicketStorage storage) {
        this.eventQueue = eventQueue;
        this.storage = storage;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("ValidateTicketConsumer");

        while (shouldRun) {
            Event eventToHandle = eventQueue.getEventIfType(EventType.ValidateTicket);
            if (eventToHandle == null) {
                continue;
            }

            if (!storage.containsTicket(eventToHandle.getTicketId())) {
                Logger.warn("Ticket is not valid.");
                continue;
            }

            Logger.info("Ticket valid.");
        }
    }
}
