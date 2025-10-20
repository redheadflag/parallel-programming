package labs3;

import utils.Logger;

public class StatisticsTask implements Runnable {

    public enum Stat {
        GOLD,
        DAMAGE
    }

    private Statistics statistics;
    private Stat stat;

    public StatisticsTask(Statistics statistics, Stat stat) {
        this.statistics = statistics;
        this.stat = stat;
    }

    @Override
    public void run() {
        Logger.debug("Incrementing " + stat + "...");

        for (int i = 0; i < 1_000_000; i++) {

            if (stat == Stat.GOLD) {
                statistics.incrementGold();
            }
            else if (stat == Stat.DAMAGE) {
                statistics.incrementDamage();
            }

        }

        Logger.debug("Done");
    }
}
