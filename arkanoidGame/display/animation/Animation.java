// 322558537 Adi Brafman
package arkanoidGame.display.animation;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that will be displayed on a DrawSurface.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public interface Animation {
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface on which to draw the animation
     */
    void doOneFrame(DrawSurface d);

    /**
     * Determines whether the animation should stop.
     *
     * @return {@code true} if the animation should stop, {@code false} otherwise
     */
    boolean shouldStop();
}
