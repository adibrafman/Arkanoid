// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.gameTools.Ball;
import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Antenna class represents an antenna background object.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Antenna implements BackgroundObject {
    private Rectangle surface;
    private Rectangle base;
    private Rectangle poll;
    private int height;
    private List<Ball> antennaLight;

    /**
     * Create a new antenna object.
     *
     * @param surface   the surface that the antenna will be on.
     * @param maxHeight the max height of the antenna.
     */
    public Antenna(Rectangle surface, int maxHeight) {
        this.surface = surface;
        this.height = (int) (maxHeight - this.surface.getHeight());
        this.base = this.createBase();
        this.poll = createPoll();
        this.antennaLight = createAntennaLight();
    }

    private Rectangle createBase() {
        int baseWidth = (int) Math.floor(this.surface.getWidth() / 3);
        int baseHeight = this.height / 5;
        int x = (int) (this.surface.getUpperLeft().getX() + ((Math.floor(this.surface.getWidth() - baseWidth) / 2)));
        int y = (int) this.surface.getUpperLeft().getY() - baseHeight;
        return new Rectangle(new Point(x, y), baseWidth, baseHeight);
    }

    private Rectangle createPoll() {
        int pollWidth = (int) Math.floor(this.base.getWidth() / 3);
        int pollHeight = (int) Math.floor(this.height - this.base.getHeight());
        int x = (int) Math.round(this.base.getUpperLeft().getX() + (this.base.getWidth() / 3));
        int y = (int) Math.floor(this.surface.getUpperLeft().getY() - this.base.getHeight() - pollHeight);
        return new Rectangle(x, y, pollWidth, pollHeight);
    }

    private List<Ball> createAntennaLight() {
        LinkedList<Ball> lights = new LinkedList<>();
        Color[] colors = {Color.orange, Color.RED, Color.WHITE};
        int r = (int) Math.round(this.poll.getWidth());
        double xCenter = this.poll.getUpperLeft().getX() + (this.poll.getWidth() / 2);
        double yCenter = this.poll.getUpperLeft().getY();
        Point center = new Point(xCenter, yCenter);
        int space = r / 3;

        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(center, r, colors[i % 3]);
            ball.setOutLineColor(colors[i % 3]);
            lights.add(ball);
            r -= space;
        }
        return lights;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.base.drawOn(d, Color.DARK_GRAY);
        this.poll.drawOn(d, Color.GRAY);

        for (Ball light : antennaLight) {
            light.drawOn(d);
        }
    }
}
