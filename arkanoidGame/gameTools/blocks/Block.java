// 322558537 Adi Brafman
package arkanoidGame.gameTools.blocks;

import arkanoidGame.Listeners.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Rectangle;
import arkanoidGame.collisionDetection.Collidable;
import arkanoidGame.sprites.Sprite;
import arkanoidGame.movement.Velocity;
import arkanoidGame.Listeners.HitListener;
import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.gameTools.Ball;

/**
 * The Block class represents a block in the game, which is both a collidable object and a sprite object.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.000001;
    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a new Block object given its rectangular shape.
     *
     * @param block the rectangular shape of the block
     */
    public Block(Rectangle block) {
        this.block = block;
        this.color = Color.PINK;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new Block object given the upper-left point of the block, width and height.
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = Color.PINK;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new Block object given its x and y coordinates, width and height.
     *
     * @param x      the x coordinate of the upper-left point of the block
     * @param y      the y coordinate of the upper-left point of the block
     * @param width  the width of the block
     * @param height the height of the block
     */
    public Block(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * Set the color of the block.
     *
     * @param color the color of the block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.block.getUpperLeft(), this.block.getWidth(), this.block.getHeight());
    }

    // Return a new Velocity to an object that a collision with it occurred at collisionPoint with a given velocity.
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        double leftX = this.block.getUpperLeft().getX();
        double topY = this.block.getUpperLeft().getY();

        // if the object hit the horizontal walls of the block
        if (this.thershold(collisionPoint.getX(), leftX)
                ||
                this.thershold(collisionPoint.getX(), leftX + this.block.getWidth())) {
            newDx = -newDx;
        }
        // if the object hit the vertical walls of the block
        if (this.thershold(collisionPoint.getY(), topY)
                ||
                this.thershold(collisionPoint.getY(), topY + this.block.getHeight())) {
            newDy = -newDy;
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    private boolean thershold(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON;
    }

    // Sprite

    // Draw the Block object
    @Override
    public void drawOn(DrawSurface d) {

        int x = (int) Math.round(this.block.getUpperLeft().getX());
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) Math.round(this.block.getWidth());
        int height = (int) Math.round(this.block.getHeight());

        // Set the color of the block
        d.setColor(this.color);
        // draw the Block
        d.fillRectangle(x, y, width, height);

        // draw the outLines
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {

    }

    // Add the block to a given game
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Remove the block from the game.
     * @param gameLevel the game to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns a String representation of the block's properties.
     *
     * @return block's properties
     */
    public String toString() {
        String block = "" + this.block;
        return block;
    }
}
