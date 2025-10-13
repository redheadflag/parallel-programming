package labs2;

import utils.Logger;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        Logger.info("Example 2");

        Thread t = new Thread(new FirstTask());

        t.start();

        t.join();

        Logger.info("Done");
    }
}
