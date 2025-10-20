package labs3;

import utils.Logger;

public class Statistics {
    private int damage = 0;
    private int gold = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void incrementDamage() {
        synchronized (lock1) {
            // synchronized(this) {} is the same as adding "synchronized" to public synchronized void...
            damage++;
        }
    }

    public void incrementGold() {
        synchronized (lock2) {
            gold++;
        }
    }

    public void print() {
        Logger.debug("Gold: " + gold + " | Damage: " + damage);
    }
}
