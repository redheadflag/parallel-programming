package labs5;


import utils.Logger;

public class UseTicketConsumer extends Thread {

    private final EventQueue eventQueue;
    private final TicketStorage storage;
    public volatile boolean shouldRun = true;

    public UseTicketConsumer(EventQueue eventQueue, TicketStorage storage) {
        this.eventQueue = eventQueue;
        this.storage = storage;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("UseTicketConsumer");

        while (shouldRun) {
            Event eventToHandle = eventQueue.getEventIfType(EventType.UseTicket);
            if (eventToHandle == null) {
                continue;
            }

            if (!storage.containsTicket(eventToHandle.getTicketId())) {
                Logger.warn("Ticket does not exist.");
                continue;
            }

            storage.removeTicket(eventToHandle.getTicketId());
            Logger.info("Ticket has been used.");
        }
    }
}
