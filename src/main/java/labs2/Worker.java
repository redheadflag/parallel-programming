package labs2;

import utils.Logger;

public class Worker extends Thread {

    public volatile boolean shouldRun = true;

    @Override
    public void run() {
        Logger.debug("Worker running...");

        while (shouldRun) {
            // do smt
        }

        Logger.debug("Worker done!");
    }
}
