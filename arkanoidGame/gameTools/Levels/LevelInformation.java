// 322558537 Adi Brafman
package arkanoidGame.gameTools.Levels;

import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.movement.Velocity;
import arkanoidGame.sprites.Sprite;
import java.util.List;


/**
 * The LevelInformation interface represents the information and specifications for a level
 * in the Arkanoid game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public interface LevelInformation {
    /**
     * Number of the balls in the game level.
     *
     * @return the number of the balls in the game level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background of the level.
     *
     * @return the background of the level
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed before the level is considered
     * to be cleared.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
