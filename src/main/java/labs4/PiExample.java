package labs4;

import utils.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class PiExample {
    public static final int TIME_CAP_MILLIS = 1000;
    public static final int THREADS = Runtime.getRuntime().availableProcessors()-1;

    public static void main(String[] args) throws InterruptedException {
        Logger.info("Calculating PI");

        Gui gui = new Gui(); // Do GUI in a separated thread!
        long start = System.currentTimeMillis();

        AtomicInteger hits = new AtomicInteger(0);
        AtomicInteger total = new AtomicInteger(0);

        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(new ThrowDarts(start, null, hits, total));
        }

        for (int i = 0; i < THREADS; i++) {
            threads[i].start();
        }

        for (int i = 0; i < THREADS; i++) {
            threads[i].join();

        }

        Logger.debug("Total: " + total);  // TODO: format this in format like 10,123,456
        Logger.debug("Hits: " + hits);

        double pi = 4.0  * hits.get() / total.get();
        Logger.info("PI: " + pi);
    }
}
