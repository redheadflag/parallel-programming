package labs5;

public class Event {
    private final int ticketId;
    private final EventType type;

    public Event(int ticketId, EventType type) {
        this.ticketId = ticketId;
        this.type = type;
    }

    public int getTicketId() {
        return ticketId;
    }

    public EventType getType() {
        return type;
    }
}
