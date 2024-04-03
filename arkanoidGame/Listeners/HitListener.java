// 322558537 Adi Brafman
package arkanoidGame.Listeners;

import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.gameTools.Ball;

/**
 * The HitListener interface represents an object that receive notifications when a block is hit by a ball.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 26/05/2023
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
