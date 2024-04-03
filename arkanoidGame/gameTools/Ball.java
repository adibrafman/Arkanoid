// 322558537 Adi Brafman
package arkanoidGame.gameTools;

import biuoop.DrawSurface;

import java.awt.Color;

import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Line;
import arkanoidGame.collisionDetection.GameEnvironment;
import arkanoidGame.collisionDetection.CollisionInfo;
import arkanoidGame.sprites.Sprite;
import arkanoidGame.movement.Velocity;

/**
 * A ball with a center point and his radius.
 *
 * @author Adi Brafman adi.brafman@gmail.com
 * @version 1.0
 * @since 04 /02/2023
 */
public class Ball implements Sprite {
    private static final int DEFAULT_R = 5;
    private static final int DEFAULT_CENTER = 500;

    // Fields
    private Point center;
    private final int r;
    private Color color;
    private Color outLineColor;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Ball object with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.outLineColor = Color.BLACK;
    }

    /**
     * Instantiates a new Ball object with the given x and y coordinates, radius, and color.
     *
     * @param x     the x coordination of the center point of the ball
     * @param y     the y coordination of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Instantiates a new Ball object with default values for the fields.
     */
    public Ball() {
        // default values for the Ball's field
        this(new Point(DEFAULT_CENTER, DEFAULT_CENTER), DEFAULT_R, Color.BLACK);
    }

    /**
     * Sets game environment.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with the given dx and dy values.
     *
     * @param dx the change in the x coordination of the ball
     * @param dy the change in the y coordination of the ball
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the velocity of the ball with a default values.
     */
    public void setVelocity() {
        this.setVelocity(-3, -3);
    }

    /**
     * Set the outLine color of the ball.
     * @param color the color of the ball's outline
     */
    public void setOutLineColor(Color color) {
        this.outLineColor = color;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the ball's center point
     */
// accessors
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the ball's center point
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * Return the ball's size (radius).
     *
     * @return the ball's size(radius)
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * Draws the ball on a given surface.
     *
     * @param surface the surface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // Set the color of the ball that will be drawn to the ball's color field
        surface.setColor(this.color);
        // draw the ball
        surface.fillCircle(this.getX(), this.getY(), r);
        surface.setColor(this.outLineColor);
        surface.drawCircle(this.getX(), this.getY(), r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Moves the ball one step in its current direction, under the limitation from his range(min and max) fields.
     * <p>
     * Moves the ball one step according to its velocity. If the ball x/y coordination cross or got to his
     * boundary(from the fields: Point min and Point max), it bounces off the wall by changing the direction of
     * his velocity:the 'dx' value - if it's a vertical wall, the 'dy' value - if it's a horizontal wall.
     * </p>
     */
    private void moveOneStep() {
        Line trajectory = computePath();
        if (!willCollide(trajectory)) {
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            CollisionInfo obstacle = this.gameEnvironment.getClosestCollision(trajectory);
            Velocity updatedV = obstacle.collisionObject().hit(this, obstacle.collisionPoint(), this.velocity);
            this.center = pointAfterHit(obstacle.collisionPoint());
            this.setVelocity(updatedV);
        }
    }

    // return the new center point of the ball after a hit with a collidable object
    private Point pointAfterHit(Point collisionPoint) {
        Velocity tmp;
        if (this.velocity.getDx() == 0) {
            tmp = new Velocity(0, -this.velocity.getDy() / Math.abs(this.velocity.getDy()));
            return tmp.applyToPoint(collisionPoint);
        } else {
            double tmpDx = -this.velocity.getDx() / Math.abs(this.velocity.getDx());
            double tmpDy = -this.velocity.getDy() / Math.abs(this.velocity.getDx());
            tmp = new Velocity(tmpDx, tmpDy);
        }

        return tmp.applyToPoint(collisionPoint);
    }

    // check if the ball will collide with other object in the game
    private boolean willCollide(Line trajectory) {
        return (this.gameEnvironment.collideInPath(trajectory).size() != 0);
    }

    private Line computePath() {
        Point afterStep = this.velocity.applyToPoint(this.center);
        return new Line(this.center, afterStep);
    }

    /**
     * Move the ball to other point after being pushed by a moving collidable object.
     *
     * @param borderX the x coordinate of the collidable object edge
     * @param borderY the y coordinate of the collidable object edge
     * @param v       the ball's new velocity calculated by the hit method of the collidable object
     */
    public void pushed(double borderX, double borderY, Velocity v) {
        this.velocity = v;
        this.center = new Point(borderX, borderY - 0.1);
    }

    /**
     * Remove the ball from game.
     *
     * @param g the game the ball need to be removed
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Returns a String representation of the ball's properties.
     *
     * @return ball's properties
     */
    public String toString() {
        return "center: " + center + " r: " + r;
    }
}
