// 322558537 Adi Brafman
package arkanoidGame.display.animation.endScreen;

import arkanoidGame.gameRunTime.Counter;

/**
 * The end screen that will be displayed when the player wins the game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class WinScreen extends EndScreen {

    /**
     * Constructs an WinScreen with the specified score and keyboard sensor.
     *
     * @param score    the player's score
     */
    public WinScreen(Counter score) {
        super(score);
    }

    @Override
    protected String getMessage() {
        return "You Win! ";
    }
}
