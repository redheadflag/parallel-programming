package labs6;

import utils.Logger;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lobby {
    private static GameRoom nextGame = new GameRoom();
    private static ExecutorService gameThreadPool = Executors.newFixedThreadPool(10);
    private static Object nextGamelock = new Object();

    private static CyclicBarrier barrier = new CyclicBarrier(4, () -> {
        synchronized (nextGamelock) {
            gameThreadPool.submit(nextGame);
            nextGame = new GameRoom();
        }
    });

    public static GameRoom join(Player player) {
        GameRoom room;

        synchronized (nextGamelock) {
            room = nextGame;
            nextGame.join(player);
        }

        try {
            barrier.await();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

        return room;
    }
}
