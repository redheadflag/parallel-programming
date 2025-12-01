package labs7;

public class Task {
    public final Message message;
    public final Peer sender;

    public Task(Message message, Peer sender) {
        this.message = message;
        this.sender = sender;
    }
}
