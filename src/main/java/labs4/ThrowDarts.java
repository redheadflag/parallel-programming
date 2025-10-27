package labs4;

import utils.Logger;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThrowDarts implements Runnable{

    private final long start;
    private final Gui gui;
    private AtomicInteger globalHits;
    private AtomicInteger globalTotal;

    public ThrowDarts(long start, Gui gui, AtomicInteger globalHits, AtomicInteger globalTotal) {
        this.start = start;
        this.gui = gui;
        this.globalHits = globalHits;
        this.globalTotal = globalTotal;
    }

    @Override
    public void run() {
        Logger.debug("Start");

        Random r = new Random();
        int hits = 0;
        int total = 0;

        while ((System.currentTimeMillis() - start) < PiExample.TIME_CAP_MILLIS) {
            float x = r.nextFloat(-1, 1);
            float y = r.nextFloat(-1, 1);

            double xs = x * x;
            double ys = y * y;
            double dist = xs + ys;

            Color color = Color.WHITE;

            if (dist <= 1) {
                color = Color.RED;
                hits++;
            }
            if (gui != null) {
                gui.drawPoint(x, y, color);
            }
            total++;
        }

        globalHits.addAndGet(hits);
        globalTotal.addAndGet(total);

        Logger.debug("Throws: " + total);
    }
}
