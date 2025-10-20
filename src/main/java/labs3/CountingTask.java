package labs3;

import utils.Logger;

public class CountingTask implements Runnable {
    Counter counter;

    CountingTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        Logger.debug("Counting...");

        for (int i = 0; i < 1_000_000; i++) {
            counter.increment();
        }

        Logger.debug("Done");
    }
}
