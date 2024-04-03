// 322558537 Adi Brafman
package arkanoidGame.display;

import arkanoidGame.display.animation.Animation;
import biuoop.DrawSurface;

/**
 * A screen displayed when the game is paused.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class PauseScreen implements Animation {

    /**
     * Constructs a new PauseScreen.
     */
    public PauseScreen() {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
