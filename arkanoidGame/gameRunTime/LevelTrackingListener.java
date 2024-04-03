// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

import arkanoidGame.Listeners.HitListener;
import arkanoidGame.display.animation.endScreen.EndScreen;
import arkanoidGame.display.animation.endScreen.LoseScreen;
import arkanoidGame.display.animation.endScreen.WinScreen;
import arkanoidGame.gameTools.Ball;
import arkanoidGame.gameTools.Levels.LevelInformation;
import arkanoidGame.gameTools.blocks.Block;

import java.util.List;

/**
 * The LevelTrackingListener class is responsible for tracking the progress of the game level,
 * including the number of blocks hit and the remaining balls. Then create the suitable EndScreen.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class LevelTrackingListener implements HitListener {
    private boolean running;
    private List<LevelInformation> levels;
    private int totalBlocks;
    private Counter totalBlockHit;
    private Counter score;
    private Counter currentLevelRemainingBalls;
    private EndScreen endGameCondition;

    /**
     * Creates a new LevelTrackingListener instance with the specified parameters.
     *
     * @param levels the list of LevelInformation objects representing the game levels
     * @param score  the Counter for keeping track of the player's score
     */
    public LevelTrackingListener(List<LevelInformation> levels, Counter score) {
        this.running = true;
        this.levels = levels;
        this.score = score;
        this.totalBlocks = sumBlocks();
        this.totalBlockHit = new Counter(0);

    }

    /**
     * Calculates and returns the total number of blocks in all game levels.
     *
     * @return the total number of blocks in all game levels
     */
    private int sumBlocks() {
        int sum = 0;
        for (LevelInformation level : levels) {
            sum += level.numberOfBlocksToRemove();
        }
        return sum;
    }

    /**
     * Notifies the LevelTrackingListener that a new level is starting.
     *
     * @param remainingBalls the Counter object representing the remaining balls in the new level
     */
    public void newLevel(Counter remainingBalls) {
        this.currentLevelRemainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        totalBlockHit.increase(1);
    }

    /**
     * Checks if the game should stop. Determines the end game condition based on the remaining balls
     * and total blocks hit.
     *
     * @return true if the game should stop, false otherwise
     */
    public boolean shouldStop() {
        if (this.currentLevelRemainingBalls.getValue() == 0) {
            this.running = false;
            this.setEndGameCondition(new LoseScreen(score));
        } else if (this.totalBlockHit.getValue() == this.totalBlocks) {
            this.running = false;
            this.setEndGameCondition(new WinScreen(score));
        }
        return !this.running;
    }

    private void setEndGameCondition(EndScreen endGameCondition) {
        this.endGameCondition = endGameCondition;
    }

    /**
     * Gets the end game condition of the LevelTrackingListener.
     *
     * @return the EndScreen representing the end game condition
     */
    public EndScreen getEndGameCondition() {
        return this.endGameCondition;
    }
}
