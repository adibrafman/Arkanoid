// 322558537 Adi Brafman
package arkanoidGame.shapes;

import java.util.List;

/**
 * A line segment with a start point, and an end point.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 04/02/2023
 */
public class Line {
    private static final double DISTANCE_THRESHOLD = 0.1;
    // small threshold value to check if two numbers are considered equal
    private static final double EPSILON = 0.000001;

    // Fields
    private Point start;
    private Point end;
    private double length;
    private double slope;
    private final double yIntersection;

    /**
     * Creates a new Line object with the given start and end Points.
     *
     * @param start the start Point of the line - the left point
     * @param end   the end Point of the line - the right point
     */
// constructors
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;

        length = this.start.distance(this.end);
        slope = this.getSlope();
        yIntersection = calculateYIntersect();
    }

    /**
     * Creates a new Line object with the given start and end coordinates.
     *
     * @param x1 the x-coordinate of the start point - the minimum 'x' value in the line
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point - the maximum 'x' value in the line
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Arrange a line so that the start point will be on the left side of the line, and the end in the right
     * <p>
     * Arranges the start and end points of the line so that the start point is to the left of (or below, if the
     * start and end points have the same 'x' value) the end point. If the start and end points are already
     * arranged in this way, no action is taken.
     * </p>
     *
     * @param l a line that need to be arranged
     * @return the given line after arrangement
     */

    public static Line arrangeLine(Line l) {
        if (l.start.getX() > l.end.getX()
                ||
                (thershold(l.start.getX(), l.end.getX()) && l.start.getY() > l.end.getY())) {
            return new Line(l.end, l.start);
        } else {
            return l;
        }
    }

    /**
     * Returns the slope of the line that is calculated by using the slope equation.
     *
     * @return the slope of the line, or Double.POSITIVE_INFINITY if the line is parallel to the y-axis
     */
// Return the slope of the line
    public double getSlope() {
        if (thershold(this.start.getX(), this.end.getX())) {
            return Double.POSITIVE_INFINITY;
        } else {
            return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
        }
    }

    /**
     * Returns the y-coordinate of the intersecting point with Y-axis. also represent the 'b' in the line
     * equation : "y = ax + b"
     *
     * @return the y-coordinate of the intersecting point with Y-axis, or Double.POSITIVE_INFINITY if the
     * line is parallel to the y-axis
     */
    public double calculateYIntersect() {
        if (Double.isInfinite(slope)) { // the line is parallel to Y axis
            return Double.POSITIVE_INFINITY;
        } else {
            //calculate the 'y' value of the intersecting by using the line equation and a point on the line
            return this.start.getY() - slope * this.start.getX();
        }
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.length;
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line as a Point object
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line as a Point object
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line as a Point object
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Check if the point is below or beneath a line.
     *
     * @param l the line to check
     * @param p the point to check
     * @return if the point is above or below the line:
     * -1 if the point is below the line, 0 if it's on the line, and 1 if it's above the line
     */
    public double sideOfPoints(Line l, Point p) {
        // Check if the point is above the line or beneath by using the slope-intercept equation of the line
        double distance = p.getY() - l.slope * p.getX() - l.yIntersection;
        if (distance < -DISTANCE_THRESHOLD) {
            return -1;
        } else if (distance > DISTANCE_THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
    }


    /**
     * Checks if the lines are intersecting.
     *
     * @param other the other line to check for intersection with this line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        //wrong input
        if (other == null) {
            return false;
        }

        other = arrangeLine(other);
        Line l = arrangeLine(this);

        // check if the lines are the same line
        if (l.equals(other)) {
            return true;

            // special condition where this line is parallel to Y axis
        } else if (Double.isInfinite(this.slope)) {
            return parallelToY(other, l.start, l.end);
            // Special condition where the other line is parallel to the Y-axis
        } else if (Double.isInfinite(other.slope)) {
            return parallelToY(l, other.start, other.end);
            // Check if the lines are parallel

        } else if (thershold(this.slope, other.slope)) {
            if (thershold(this.yIntersection, other.yIntersection)) {
                // check if they are a separate segments on the same line
                return !(l.end.getX() < other.start.getX() || other.end.getX() < l.start.getX());
            } else {
                // if the lines are parallel but not have the same intersection point with Y axis
                return false;
            }
        }
        //checking if one point of line is above the other line, and one point is below
        return !(sideOfPoints(other, l.start) * sideOfPoints(other, l.end) > 0)
                &&
                !(sideOfPoints(l, other.start) * sideOfPoints(l, other.end) > 0);
    }

    /**
     * check if Line 'l'  is intersecting with other line (that is parallel to Y-axis) which is represented by
     * Point 'start' and Point 'end'.
     *
     * @param l     the line to check intersect with
     * @param start the start point (with the lower y coordinate) of the line that is parallel to Y-axis
     * @param end   the end point (with the higher y coordinate)of the line to check for intersection with
     * @return true if the lines are intersecting, false otherwise.
     */
    private boolean parallelToY(Line l, Point start, Point end) {
        double aParallel = start.getX(); // the 'a' value of the line: x=a
        //check if the parallel line is in the x-coordinate range of the 'l's start and end points
        if (aParallel < l.start.getX() || aParallel > l.end.getX()) {
            return false;
            // special condition where 'l' is a vertical line
        } else if (Double.isInfinite(l.getSlope())) {
            return (l.start.distance(start) <= l.length);
        } else {
            // check if the other line intersects with 'l'
            return !(sideOfPoints(l, start) * sideOfPoints(l, end) > 0);
        }
    }

    /**
     * The method check if one line is include the other, by checking if one line ends when the other start.
     * <p>
     * The lines' Points are arrange as that the 'x' value of {@code start} < 'x' value of {@code end},
     * (if the line is parallel to Y-axis, this rule is apply on the points' 'y' value)
     * In order to check if there is an inclusion, we need to check only if one line is ending when the other start.
     * </p>
     *
     * @param other the other line
     * @param l     this line when the start point is the left point, and the end point is the right point
     * @return {@code false} if they are meeting in only one point, {@code true} if one line is include other
     */
    public boolean isInclude(Line other, Line l) {
        if (Double.isInfinite(other.slope)) {
            return !(thershold(l.end.getY(), other.start.getY()) || thershold(other.end.getY(), l.start.getY()));
        } else {
            return !(thershold(l.end.getX(), other.start.getX()) || thershold(other.end.getX(), l.start.getX()));
        }
    }

    /**
     * Returns the intersecting point between two lines segments that have the same line's
     * equation and meet in only one point.
     *
     * @param otherStart   the start point of the other line
     * @param thisArranged this line when the start point is the left point, and the end point is the right point
     * @return the intersecting point
     */
    public Point sameLineMeeting(Point otherStart, Line thisArranged) {
        if (thisArranged.end.equals(otherStart)) {
            return thisArranged.end;
        } else {
            return thisArranged.start;
        }
    }

    /**
     * Calculates the intersection point of two finite lines.
     *
     * @param other the other line
     * @param l     this line when the start point is the left point, and the end point is the right point
     * @return the intersection point of the two finite lines, or null if they do not intersect
     */
    public Point calculateIntersect(Line l, Line other) {
        double intersectX;
        double intersectY;

        if (Double.isFinite(this.slope) && Double.isFinite(other.slope)) {
            intersectX = (other.yIntersection - this.yIntersection) / (this.slope - other.slope);
            intersectY = this.slope * intersectX + this.yIntersection;
        } else {
            intersectX = Math.max(l.start.getX(), other.start.getX());
            intersectY = Math.min(this.slope, other.slope) * intersectX
                    +
                    Math.min(this.yIntersection, other.yIntersection);
        }

        return new Point(intersectX, intersectY);
    }

    /**
     * Finds the point of intersection between two lines.
     *
     * @param other the other line to check
     * @return the point of intersection between the lines, or null if the lines do not intersect or in the case
     * of inclusion
     */
    public Point intersectionWith(Line other) {

        other = arrangeLine(other);
        Line l = arrangeLine(this);

        // Check if the lines intersect
        if (!isIntersecting(other)) {
            return null;
        } else if ((Double.isInfinite(this.slope) && Double.isInfinite(other.slope))
                || thershold(this.slope, other.slope)) {
            if (!isInclude(other, l)) {
                return sameLineMeeting(other.start, l);
            } else {
                return null;
            }
        }
        return calculateIntersect(l, other);
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                ||
                (this.end.equals(other.start) && this.start.equals(other.end));
    }

    /**
     * Compare doubles for equality- if the absolute difference between two numbers is less than a small threshold value
     *
     * <p>
     * This method is used to compare two double values for equality, since the operator == can be inaccurate due to
     * round-off errors. The method checks if the absolute difference between num1 and num2 is less than a small
     * threshold value (0.000001), and returns true if this condition has been fulfilled.
     * </p>
     *
     * @param num1 the first number to compare
     * @param num2 the second number to compare
     * @return {@code true} if the absolute difference is less than the threshold value, {@code false} otherwise
     * }
     */
    private static boolean thershold(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON;
    }


    /**
     * Check for intersection between this line and a given rectangle, and return the closest point to the start of
     * the line.
     *
     * @param rect a rectangle to check intersection with
     * @return the closest intersection point to the start of the line, and null If this line does not intersect
     * with the rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectPoints = rect.intersectionPoints(this);
        if (intersectPoints.isEmpty()) {
            return null;
        } else {
            return minDis(this.start, intersectPoints);
        }
    }

    /**
     * find the closest point to a given point from a list of points.
     *
     * @param from   the reference point to compare distances from
     * @param points the list of points to search for the closest point
     * @return the closest point in the list to the reference point
     */
    private Point minDis(Point from, List<Point> points) {
        double minDistance = Double.POSITIVE_INFINITY;
        Point closestPoint = null;
        for (Point point : points) {
            double distance = from.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * Returns a String representation of the line in the form of its equation.
     *
     * @return a String representation of the line in the form of its equation.
     */
    public String toString() {
        String lineEquasion = " ";
        String yIntersect = " ";

        if (this.yIntersection > 0) {
            yIntersect = " + " + this.yIntersection;
        } else if (this.yIntersection < 0) {
            yIntersect = " - " + Math.abs(this.yIntersection);
        }

        if (Double.isInfinite(slope)) {
            lineEquasion = "x = " + this.start.getX(); // "x = constant"
        } else if (thershold(slope, 0)) {
            lineEquasion = "y = " + this.start.getY(); // "y = constant"
        } else {
            lineEquasion = "y = " + slope + "x" + yIntersect; // "y = mx + b"
        }
        return lineEquasion;
    }
}