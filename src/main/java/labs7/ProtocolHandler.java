package labs7;

import utils.Logger;

import java.util.ArrayList;

public class ProtocolHandler extends Thread {
    private final ArrayList<String> history = new ArrayList<>();

    @Override
    public void run() {
        Thread.currentThread().setName("ProtocolHandler");

        while (true) {
            Task task = TaskQueue.getTask();

            if (task == null) {
                continue;
            }

            if (history.contains(task.message.id)) {
                continue;
            }

            history.add(task.message.id);

            switch (task.message.type) {
                case CHAT -> handleChatTask(task);
                case PEER_DISCOVERY_REQUEST -> handlePeerDiscoveryRequest(task);
                case PEER_DISCOVERY_RESPONSE -> handlePeerDiscoveryResponse(task);
            }
        }
    }

    private void handlePeerDiscoveryResponse(Task task) {
        String[] ips = task.message.content.split(";");

        for (String ip : ips) {
            if (!ip.equals(Example.MY_IP)) {
                PeerList.connectToPeer(ip);
            }
        }
    }

    private void handlePeerDiscoveryRequest(Task task) {
        int ipsToPull = Integer.parseInt(task.message.content);

        String[] ips = PeerList.getIps(ipsToPull);
        String body = String.join(";", ips);

        task.sender.sendMessage(new Message(
                MessageType.PEER_DISCOVERY_RESPONSE,
                body
        ));
    }

    private void handleChatTask(Task task) {
        Thread.currentThread().setName(task.message.sender);
        Logger.debug(task.message.content);
        Thread.currentThread().setName("ProtocolHandler");

        PeerList.broadcast(task.message);
    }
}
