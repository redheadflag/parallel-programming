package labs3;

import utils.Logger;

public class Example1 {
    public static void main(String[] args) {
        Logger.info("Example 1");

        Counter counter = new Counter();

        Thread t1 = new Thread(new CountingTask(counter));
        Thread t2 = new Thread(new CountingTask(counter));
        Thread t3 = new Thread(new CountingTask(counter));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e) {
            Logger.error("Interrupted: " + e.getMessage());
        }

        Logger.info("Done with " + counter.getCount());
    }
}
