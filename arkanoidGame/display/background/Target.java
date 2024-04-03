// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.gameTools.Ball;
import arkanoidGame.movement.Velocity;
import arkanoidGame.shapes.Line;
import arkanoidGame.shapes.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * A target that will be drawn on a background.
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Target implements BackgroundObject {
    private List<Ball> targetCircles;
    private List<Line> targetLines;
    private Point center;
    private int totalSize;

    /**
     * Instantiates a new Target object.
     *
     * @param center    the center of the target.
     * @param totalSize the total size for the whole target object.
     */
    public Target(Point center, int totalSize) {
        this.center = center;
        this.totalSize = totalSize;
        this.targetCircles = new LinkedList<>();
        this.targetLines = new LinkedList<>();
        this.create();
    }

    private void create() {
        this.createTargetCircle();
        this.createTargetLines();
    }

    private void createTargetCircle() {
        int r = (int)(totalSize / 2.5);
        int gap = (r / 3);
        for (int i = 0; i < 3; i++) {
            this.targetCircles.add(new Ball(center, r, Color.BLUE));
            r -= gap;
        }
    }

    private void createTargetLines() {
        int distanceFromTheCenter = (((this.totalSize / 6) * 4) + 3) / 12;
        int length = this.totalSize / 2;
        this.targetLines.add(this.locatePoints(distanceFromTheCenter, length, 0, 0));
        this.targetLines.add(this.locatePoints(-distanceFromTheCenter, -length, 0, 0));
        this.targetLines.add(this.locatePoints(0, 0, distanceFromTheCenter, length));
        this.targetLines.add(this.locatePoints(0, 0, -distanceFromTheCenter, -length));
    }

    private Line locatePoints(int dxStart, int dxEnd, int dyStart, int dyEnd) {
        Point start = new Velocity(dxStart, dyStart).applyToPoint(this.center);
        Point end = new Velocity(dxEnd, dyEnd).applyToPoint(start);
        return new Line(start, end);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        for (Ball circle : targetCircles) {
            d.drawCircle(circle.getX(), circle.getY(), circle.getSize());
        }
        for (Line line : targetLines) {
            d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                    (int) line.end().getX(), (int) line.end().getY());
        }
    }
}
