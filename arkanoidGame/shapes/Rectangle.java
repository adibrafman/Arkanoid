// 322558537 Adi Brafman
package arkanoidGame.shapes;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle object that has a location point, width, and height.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class Rectangle {
    public static final int REC_EDGES = 4;
    public static final int UP_LEFT = 0;
    public static final int DOWN_LEFT = 1;
    public static final int UP_RIGHT = 2;
    public static final int DOWN_RIGHT = 3;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location, width and height.
     *
     * @param upperLeft the location of the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Create a new rectangle with x and y coordinate, width and height.
     *
     * @param x      the x coordinate of the upper left point of the rectangle
     * @param y      the y coordinate of the upper left point of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Return an array of points representing the four corners of the rectangle.
     *
     * @return the corner points array.
     */
    private Point[] recEdges() {
        return new Point[]{upperLeft, // upper left point
                new Point(upperLeft.getX(), upperLeft.getY() + getHeight()), // lower left Point
                new Point(upperLeft.getX() + width, upperLeft.getY()), // upper right Point
                new Point(upperLeft.getX() + width, upperLeft.getY() + getHeight())};
    }

    /**
     * Create an array of lines representing the four sides of the rectangle, organized in clockwise order.
     *
     * @param edges an array of four points representing the corners of the rectangle
     * @return an array of four lines representing the sides of the rectangle
     */
    private Line[] recLine(Point[] edges) {
        Line[] lines = new Line[REC_EDGES];

        lines[0] = new Line(edges[UP_LEFT], edges[UP_RIGHT]); //upper side of the Rectangle
        lines[1] = new Line(edges[UP_RIGHT], edges[DOWN_RIGHT]); //right side of the Rectangle
        lines[2] = new Line(edges[DOWN_RIGHT], edges[DOWN_LEFT]); //lower side of the Rectangle
        lines[3] = new Line(edges[DOWN_LEFT], edges[UP_LEFT]); //left side of the Rectangle

        return lines;
    }

    /**
     * Returns a (possibly empty) list of intersection points of a given line with the rectangle.
     *
     * @param line the line to check intersection with the rectangle
     * @return a list of intersection points with the given line
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] recSides = recLine(recEdges());
        List<Point> intersectPoints = new ArrayList<>();
        for (int i = 0; i < REC_EDGES; i++) {
            Point intersect = line.intersectionWith(recSides[i]);
            if (intersect != null) {
                intersectPoints.add(intersect);
            }
        }
        return intersectPoints;
    }

    /**
     * Checks if a given point is inside the rectangle.
     *
     * @param point the point to check
     * @return {@code true} if the point is in the rectangle, and {@code false} otherwise
     */
    public boolean isIncludePoint(Point point) {
        return this.includeX(point.getX()) && this.includeY(point.getY());
    }

    /**
     * Check if the x coordinate of a point is within the rectangle's x coordinate range.
     *
     * @param pointX the x coordinate of a point
     * @return {@code true} if the point's x coordinate is within the rectangle, and {@code false} otherwise
     */
    private boolean includeX(double pointX) {
        return pointX >= this.getUpperLeft().getX() && pointX <= this.getUpperLeft().getX() + this.width;
    }

    /**
     * Check if the y coordinate of a point is within the rectangle's y coordinate range.
     *
     * @param pointY the y coordinate of a point
     * @return {@code true} if the point's y coordinate is within the rectangle, and {@code false} otherwise
     */
    private boolean includeY(double pointY) {
        return pointY >= this.upperLeft.getY() && pointY <= this.upperLeft.getY() + this.getHeight();
    }

    public void drawOn(DrawSurface d, Color color){
        d.setColor(color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Returns a String representation of this rectangle with his width, height and upper left point.
     *
     * @return the string representation of this rectangle
     */
    public String toString() {
        return "upper Left Point: " + this.upperLeft + ", width: " + this.width + ", height: " + this.height;
    }
}
