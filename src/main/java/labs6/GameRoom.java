package labs6;

import utils.Logger;

import java.util.ArrayList;
import java.util.Random;

public class GameRoom implements Runnable {
    private final String name = "GameRoom " + new Random().nextInt(10000);
    private final ArrayList<Player> players = new ArrayList<>();

    public synchronized void join(Player player) {
        players.add(player);
    }

    public synchronized void leave(Player player) {
        players.remove(player);
    }

    public synchronized int getPlayerCount() {
        return players.size();
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        Logger.info("Starting...");

        while (getPlayerCount() > 0) {
            Logger.debug(getPlayerCount() + " players playing...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.error(e.getMessage());
            }
        }

        Logger.info("The game has been finished!");
    }
}
