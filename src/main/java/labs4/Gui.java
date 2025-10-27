package labs4;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel panel;

    private final int FRAME_SIZE = 800;

    public Gui() {
        frame = new JFrame("Calculating PI");
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 40);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void drawPoint(float x, float y, Color color) {
        Graphics2D g2d = (Graphics2D) panel.getGraphics();

        int interpolatedX = (int) ((float) FRAME_SIZE / 2 * x) + FRAME_SIZE / 2;
        int interpolatedY = (int) ((float) FRAME_SIZE / 2 * y) + FRAME_SIZE / 2;

        g2d.setColor(color);
        g2d.fillOval(interpolatedX - 5, interpolatedY - 5, 10, 10);
    }
}
