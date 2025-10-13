package labs2;

import utils.Logger;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        Logger.info("Example 1");

        Thread t = new FirstWorker();
        t.start();

        // Parallel work

        try {
            t.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Logger.info("Done");
    }
}
