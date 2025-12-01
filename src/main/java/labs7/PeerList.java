package labs7;

import utils.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class PeerList {

    private static ArrayList<Peer> peers = new ArrayList<>();

    public synchronized static void addPeer(Peer peer) {
        peers.add(peer);
    }

    public synchronized static void removePeer(Peer peer) {
        peers.remove(peer);
    }

    public synchronized static void broadcast(Message message) {
        for (Peer peer : peers) {
            peer.sendMessage(message);
        }
    }

    public static void connectToPeer(String ip) {
        Socket socket = null;

        try {
            socket = new Socket(ip, 7777);
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return;
        }

        Peer peer = null;
        try {
            peer = new Peer(socket);
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return;
        }

        Server.threadPool.submit(peer);

        if (peers.size() < 2) {
            peer.sendMessage(new Message(MessageType.PEER_DISCOVERY_REQUEST, "2"));
        }
    }

    public static synchronized String[] getIps(int max) {
        int numOfIps = Math.min(max, peers.size());
        String[] ips = new String[max];

        Collections.shuffle(peers);

        for (int i = 0; i < numOfIps; i++) {
            ips[i] = peers.get(i).getIp();
        }

        return ips;

    }
}
