// 322558537 Adi Brafman
package arkanoidGame.display.animation.endScreen;

import arkanoidGame.gameRunTime.Counter;
import biuoop.DrawSurface;
import arkanoidGame.display.animation.Animation;

/**
 * Display a screen with the final score, and an announcement if the player won/lost.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public abstract class EndScreen implements Animation {
    private static final String SCORE_MESSAGE = "Your score is ";
    private Counter score;
    /**
     * Constructs an EndScreen with the specified score and keyboard sensor.
     *
     * @param score    the player's score
     */
    public EndScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String message = getMessage() + SCORE_MESSAGE + score.getValue();

        d.drawText(10, d.getHeight() / 2, message, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Getting the message that will be displayed if the player win/ lost.
     *
     * @return the message
     */
    protected abstract String getMessage();
}
