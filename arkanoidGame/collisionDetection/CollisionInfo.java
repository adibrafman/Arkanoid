// 322558537 Adi Brafman
package arkanoidGame.collisionDetection;

import arkanoidGame.shapes.Point;

/**
 * The CollisionInfo class represents information about a collision between two object in the environment.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class CollisionInfo {
    private Point collisionPoint; //The point of the collision
    private Collidable collisionObject; // The collidable object in the collision.

    /**
     * Constructs a new CollisionInfo object.
     *
     * @param collisionPoint  the point of the collision
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point of the collision.
     *
     * @return the point of the collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
