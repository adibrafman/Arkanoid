package arkanoidGame.shapes;
// 322558537 Adi Brafman

/**
 * A point with x and y coordination.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 04/02/2023
 */
public class Point {

    private static final double EPSILON = 0.000001;
    // Fields
    private double x;
    private double y;

    /**
     * Creates a new Point object with a specified x and y coordination.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return the x coordinate of the point.
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return the y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Distance between this point to other point.
     * <p>
     * calculate the distance according to the distance's formula between two points.
     * </p>
     *
     * @param other the other point.
     * @return the distance between this point and the other point.
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {

        if (other == null) { //false input
            return 0;
        }

        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) { //false input
            return false;
        }
        return this.thershold(this.getX(), other.getX()) && this.thershold(this.getY(), other.getY());
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
    private boolean thershold(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON;
    }

    /**
     * Return the point representation in coordinates "(x,y)".
     *
     * @return the point representation in coordinates "(x,y)"
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
