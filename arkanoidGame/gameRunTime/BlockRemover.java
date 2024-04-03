// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

import arkanoidGame.Listeners.HitListener;
import arkanoidGame.gameTools.Ball;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.gameTools.GameLevel;

/**
 * Remove blocks from the game, and count the number of the remaining blocks.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 26/05/2023
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Creates a new BlockRemover with a given game and counter of removed blocks.
     *
     * @param gameLevel          the game from which blocks will be removed
     * @param removedBlocks the counter of removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
