// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

import arkanoidGame.Listeners.HitListener;
import arkanoidGame.gameTools.Ball;
import arkanoidGame.gameTools.blocks.Block;

/**
 * Update the score counter when blocks are being hit and removed.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 26/05/2023
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter to track
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);

    }


}
