// 322558537 Adi Brafman
package arkanoidGame.display.animation.endScreen;

import arkanoidGame.gameRunTime.Counter;

/**
 * The end screen that will be displayed when the player loses the game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class LoseScreen extends EndScreen {

    /**
     * Constructs an LoseScreen with the specified score and keyboard sensor.
     *
     * @param score    the player's score
     */
    public LoseScreen(Counter score){
        super(score);
    }

    @Override
    protected String getMessage() {
        return "Game Over. ";
    }
}
