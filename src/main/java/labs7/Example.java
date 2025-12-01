package labs7;

import utils.Logger;

public class Example {
    public static final String MY_IP = "172.16.154.53";
    public static final String BOOTSTRAP_IP = "172.16.154.10";
    public static final String USERNAME = "Ivan";

    public static void main(String[] args) {
        Logger.info("Decentralized chat system");

        new Server().start();
        new UserInput().start();
        new ProtocolHandler().start();

        if (!MY_IP.equals(BOOTSTRAP_IP)) {
            PeerList.connectToPeer(BOOTSTRAP_IP);
        }
    }
}
