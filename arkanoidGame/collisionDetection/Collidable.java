// 322558537 Adi Brafman
package arkanoidGame.collisionDetection;

import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Rectangle;
import arkanoidGame.movement.Velocity;
import arkanoidGame.gameTools.Ball;

/**
 * The Collidable interface represents rectangle shape object that can collide with other objects.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public interface Collidable {
    /**
     * Returns the shape of the collidable object.
     *
     * @return the collision shape of the collidable object as a Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision with this object and updates the velocity of the other colliding object.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object that collided with this
     *                        Collidable object before the hit.
     * @param hitter the ball that doing the hit
     * @return the new velocity of the colliding object after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
