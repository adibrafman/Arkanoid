// 322558537 Adi Brafman
package arkanoidGame.collisionDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import arkanoidGame.shapes.Line;
import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Rectangle;

/**
 * The GameEnvironment class represents a collection of Collidable objects, to check if an object had collide with
 * one of them, and return this collidable object.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class GameEnvironment {
    private List<Collidable> collideObj;

    /**
     * Create a list of collidable objects in the environment.
     */
    public GameEnvironment() {
        this.collideObj = new LinkedList<>();
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add to the environment
     */
    public void addCollidable(Collidable c) {
        this.collideObj.add(c);
    }

    /**
     * Adds the list of collidable objects to the environment.
     *
     * @param c the list of collidable objects to add to the environment
     */
    public void addCollidable(List<Collidable> c) {
        this.collideObj.addAll(c);
    }

    /**
     * Finds the closest collision between the given trajectory and any of the
     * collidable objects in the environment.
     *
     * @param trajectory the line representing the object's movement
     * @return the information about the closest collision that is going to occur, or null if there isn't one.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // initializing the variable
        double minDistance = Double.POSITIVE_INFINITY;
        CollisionInfo closestCollisionInfo = null;

        // get a list of Collidable objects that if moving on the trajectory will hit them
        List<CollisionInfo> futureCollide = this.collideInPath(trajectory);

        // look for the closest Collision
        for (CollisionInfo collisionInfo : futureCollide) {
            double distance = trajectory.start().distance(collisionInfo.collisionPoint());
            if (distance < minDistance) {
                minDistance = distance;
                closestCollisionInfo = collisionInfo;
            }
        }
        return closestCollisionInfo;
    }

    /**
     * Finds the list of collidable objects that will collide with an object in one step.
     *
     * @param trajectory the line representing the object's movement
     * @return the list of collidable objects that will collide with the given trajectory
     */
    public List<CollisionInfo> collideInPath(Line trajectory) {
        List<CollisionInfo> futureCollide = new LinkedList<>();
        List<Collidable> collidables = new ArrayList<>(this.collideObj);

        for (Collidable collidable : collidables) {
            Rectangle collisionRec = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collisionRec);
            // if the object is on the line path - add them to the list
            if (collisionPoint != null) {
                futureCollide.add(new CollisionInfo(collisionPoint, collidable));
            }
        }
        return futureCollide;
    }

    /**
     * Remove a Collidable from the 'collideObj' list.
     * @param c the Collidable object to remove
     */
    public void removeFromEnvironment(Collidable c) {
        collideObj.remove(c);
    }
}
