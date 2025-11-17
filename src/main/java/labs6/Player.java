package labs6;

import utils.Logger;

import java.util.Random;

public class Player implements Runnable {
    private final Random r = new Random();
    private final String name = "Player " + r.nextInt(10000);

    private boolean connected = true;
    private GameRoom gameRoom = null;

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        Logger.info("Connected...");

        while (connected) {
            if (shouldDisconnect()) {
                Logger.warn("Disconnected");
                if (gameRoom != null) {
                    gameRoom.leave(this);
                    gameRoom = null;
                }
                connected = false;
            }

            if (shouldJoinGame()) {
                gameRoom = Lobby.join(this);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.error(e.getMessage());
            }
        }
    }

    private boolean shouldJoinGame() {
        return gameRoom == null && r.nextBoolean();
    }

    private boolean shouldDisconnect() {
        return connected && r.nextFloat() < 0.1;
    }
}
