package labs7;

import utils.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    public static ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void run() {
        Thread.currentThread().setName("Server");

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return;
        }

        Logger.info("Server listening on port 7777");

        while (true) {
            Socket connection = null;

            try {
                connection = serverSocket.accept();
            } catch (IOException e) {
                Logger.error(e.getMessage());
                continue;
            }

            Logger.info("-------------- New connection -------------");
            Logger.info("-> Local IP: " + connection.getLocalAddress());
            Logger.info("-> Local Port: " + connection.getLocalPort());
            Logger.info("-> Remote IP: " + connection.getRemoteSocketAddress());
            Logger.info("-> Remote Port: " + connection.getPort());
            Logger.info("-------------------------------------------");

            try {
                threadPool.submit(new Peer(connection));
            } catch (IOException e) {
                Logger.error(e.getMessage());
            }

        }
    }
}
