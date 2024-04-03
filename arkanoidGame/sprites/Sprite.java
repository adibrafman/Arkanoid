// 322558537 Adi Brafman
package arkanoidGame.sprites;

import biuoop.DrawSurface;
import arkanoidGame.gameTools.GameLevel;

/**
 * The Sprite interface represents an object that can be drawn on the screen and can and can be notified that
 * time has passed (so they can change their position).
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds the sprite to a given game.
     *
     * @param g the Game to add the sprite to.
     */
    void addToGame(GameLevel g);
}