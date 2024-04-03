// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

import arkanoidGame.Listeners.HitListener;
import arkanoidGame.gameTools.Ball;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.gameTools.GameLevel;

/**
 * The BallRemover class is responsible for removing balls from the game and keeping track of the remaining balls.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Creates a new BallRemover with the given game and counter of removed balls.
     *
     * @param gameLevel    the game from which balls will be removed
     * @param removedBalls the counter of removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);
    }
}
