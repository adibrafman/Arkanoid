// 322558537 Adi Brafman
package arkanoidGame.display.background;


import biuoop.DrawSurface;

/**
 * An object that can be drawn on a background.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public interface BackgroundObject {
    /**
     * Draws the object on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);
}
