package labs4;

import utils.Logger;

import java.awt.*;
import java.util.Random;

public class PiExample {
    public static final int TIME_CAP_MILLIS = 1000;

    public static void main(String[] args) {
        Logger.info("Calculating PI");

        Gui gui = new Gui();
        Random r = new Random();

        int hits = 0;
        int total = 0;

        long start = System.currentTimeMillis();

        while ((System.currentTimeMillis() - start) < TIME_CAP_MILLIS) {
            float x = r.nextFloat(-1, 1);
            float y = r.nextFloat(-1, 1);

            double xs = x * x;
            double ys = y * y;
            double dist = Math.sqrt(xs + ys);

            Color color = Color.WHITE;

            if (dist <= 1) {
                color = Color.RED;
                hits++;
            }

            gui.drawPoint(x, y, color);
            total++;
        }

        Logger.debug("Total: " + total);
        Logger.debug("Hits: " + hits);

        double pi = 4.0  * hits / total;
        Logger.info("PI: " + pi);
    }
}
