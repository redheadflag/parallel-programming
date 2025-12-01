package labs7;

import utils.Logger;

import java.io.*;
import java.net.Socket;

public class Peer implements Runnable{

    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public Peer(Socket socket) throws IOException {
        this.socket = socket;

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        OutputStreamWriter osr = new OutputStreamWriter(socket.getOutputStream());

        reader = new BufferedReader(isr);
        writer = new BufferedWriter(osr);
    }

    private String readMessage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            Logger.error("Error reading message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName(socket.getInetAddress().getHostName());
        PeerList.addPeer(this);
        Logger.info("Connected");

        while (true) {
            String rawMessage = readMessage();
            if (rawMessage == null) {
                break;
            }

            Message message = null;
            try {
                message = new Message(rawMessage);
            }
            catch (Exception e) {
                Logger.error("Error parsing raw message: " + e.getMessage());
            }

            TaskQueue.addTask(new Task(message, this));
        }

        PeerList.removePeer(this);
        Logger.warn("Disconnected...");
    }

    public void sendMessage(Message message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            Logger.error("Error sending message: " + e.getMessage());
        }
    }

    public String getIp() {
        return socket.getInetAddress().getHostAddress();
    }
}
