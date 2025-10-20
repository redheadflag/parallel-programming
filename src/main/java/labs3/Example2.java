package labs3;

import utils.Logger;

public class Example2 {

    public static void main(String[] args) {
        Logger.info("Example 2");

        incrementOneResource();
        incrementBothResources();

        incrementSingleThreaded();

        Logger.info("Done");
    }

    private static void incrementSingleThreaded() {
        Logger.info("Incrementing single threading");

        long start = System.currentTimeMillis();
        
        int damage = 0;
        int gold = 0;

        for (int i = 0; i < 2_000_000; i++) {
            damage += 1;
        }

        for (int i = 0; i < 2_000_000; i++) {
            gold += 1;
        }
        
        long end = System.currentTimeMillis();

        Logger.info("Done in " + (end - start) + " ms");
    }

    private static void incrementOneResource() {
        Logger.info("Incrementing one resource");
        long start = System.currentTimeMillis();

        Statistics statistics = new Statistics();
        Thread t1 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));
        Thread t2 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));
        Thread t3 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));
        Thread t4 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (InterruptedException e) {
            Logger.error(e.getMessage());
        }

        long end = System.currentTimeMillis();
        statistics.print();
        Logger.info("Done in " + (end-start) + "ms");
    }

    private static void incrementBothResources() {
        Logger.info("Incrementing both resources");
        long start = System.currentTimeMillis();

        Statistics statistics = new Statistics();
        Thread t1 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));
        Thread t2 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.GOLD));
        Thread t3 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.DAMAGE));
        Thread t4 = new Thread(new StatisticsTask(statistics, StatisticsTask.Stat.DAMAGE));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (InterruptedException e) {
            Logger.error(e.getMessage());
        }

        long end = System.currentTimeMillis();
        statistics.print();
        Logger.info("Done in " + (end-start) + "ms");
    }
}
