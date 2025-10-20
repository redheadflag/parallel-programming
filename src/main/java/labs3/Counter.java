package labs3;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }


}
