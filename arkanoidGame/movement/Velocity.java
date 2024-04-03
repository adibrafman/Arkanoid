package arkanoidGame.movement;

import arkanoidGame.shapes.Point;


// 322558537 Adi Brafman

/**
 * Velocity specifies the change in position on the 'x' and 'y' coordination.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 04/02/2023
 */
public class Velocity {
    private static final int MAX_VELOCITY = 100;
    private static final int DEFAULT = 7;

    private double dx;
    private double dy;

    /**
     * Constructs a new Velocity object with the specified change in 'x' and 'y' coordinates.
     *
     * @param dx the change in the x coordination.
     * @param dy the change in the y coordination.
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

        //wrongInput();
    }

    /**
     * Constructs a new Velocity object with default values for 'dx' and 'dy'.
     */
    public Velocity() {
        this(DEFAULT, DEFAULT);
    }

    /**
     * Creates a new Velocity object from a given angle and speed.
     * <p>
     * The method gets angle in degrees (0 degrees represents upwards, and increases clockwise), and speed
     * which represents the speed of the Velocity in units per step.
     * Then, convert the 'Angle' to radians to be able to use 'Math.cos()' and 'Math.sin()'. then by using
     * trigonometry find the 'dx' and 'dy'.
     * </p>
     *
     * @param angle The angle in degrees
     * @param speed speed The speed of the Velocity. Represented as the hypotenuse while we're doing trigonometry
     * @return A Velocity object calculated from the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed; // Negative sign to account for the inverted y-axis
        return new Velocity(dx, dy);
    }

    /*/**
     * Maximal and minimal values of Velocity fields are defined as 100/-100(respectively). The method check if
     * the new Velocity values are standing in this term.  if not - changing it to a default values
     * and printing an exception.
     */
    /*public void wrongInput() {
        if (Math.abs(this.dx) > MAX_VELOCITY) {
            this.dx = MAX_VELOCITY;
            System.out.println(dx + " is invalid value for dx. Maximal value is 100, and minimal is -100");
        }
        if (Math.abs(this.dy) > MAX_VELOCITY) {
            System.out.println(dy + " is invalid value for dy. Maximal value is 100, and minimal is -100");
            this.dy = MAX_VELOCITY;
        }
    }*/

    /**
     * Gets dx.
     *
     * @return change in position on the 'x' coordination
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return change in position on the 'y' coordination
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point in position (x,y)
     * @return the point in the new position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}

