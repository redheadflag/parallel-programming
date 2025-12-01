package labs7;

import java.util.UUID;

public class Message {
    public final String id;
    public final MessageType type;
    public final String sender;
    public final String content;

    public Message(MessageType type, String content) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.content = content;
        this.sender = Example.USERNAME;
    }

    public Message(String rawMessage) {
        String[] parts = rawMessage.split(" ", 4);

        this.id = parts[0];
        this.type = MessageType.valueOf(parts[1]);
        this.sender = parts[2];
        this.content = parts[3];
    }

    public String toString() {
        return id + " " + type + " " + sender + " " + content;
    }
}
