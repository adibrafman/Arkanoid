// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.display.Frame;
import arkanoidGame.gameTools.Ball;
import arkanoidGame.shapes.Line;
import arkanoidGame.shapes.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Sun class represents a sun that will be drawn on a background.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Sun implements BackgroundObject {
    private static final int NUM_RAYS = 115; // Number of rays
    private static final Color PALE_YELLOW = new Color(255, 239, 128);
    private static final Color DARK_YELLOW = new Color(209, 179, 0);
    private static final Color YELLOW = new Color(232, 198, 0);
    private static final int CIRCLE_NUM = 3;
    private Point center;
    private int width;
    private int height;
    private List<Ball> circles;
    private List<Line> rays;

    /**
     * Instantiates a new Sun object.
     *
     * @param width  the width of the sun
     * @param height the height of the sun
     */
    public Sun(int width, int height) {
        this.width = width;
        this.height = height;
        this.center = new Point((double) this.width / 5, (double) this.height / 2);
        this.circles = new LinkedList<>();
        this.rays = new LinkedList<>();
        create();
    }

    private void create() {
        Color[] circlesColor = {PALE_YELLOW, DARK_YELLOW, YELLOW};
        this.createCircles(circlesColor);
        this.createRays();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(PALE_YELLOW);
        for (Line ray : rays) {
            d.drawLine((int) ray.start().getX(), (int) ray.start().getY(),
                    (int) ray.end().getX(), (int) ray.end().getY());
        }
        for (Ball circle : circles) {
            circle.drawOn(d);
        }
    }

    private void createCircles(Color[] colors) {
        int r = (int) ((this.height / 6) * 1.5);
        int gap = r / 5;
        for (int i = 0; i < CIRCLE_NUM; i++) {
            Ball ball = new Ball(center, r, colors[i]);
            ball.setOutLineColor(colors[i]);
            this.circles.add(ball);
            r -= gap;
        }
    }

    private void createRays() {
        int step = 6;
        Point ray = new Point(Frame.FRAME_SIZE, this.height);
        for (int i = 0; i < NUM_RAYS; i++) {
            Line line = new Line(center, ray);
            ray = new Point(ray.getX() + step, ray.getY());
            rays.add(line);
        }
    }
}