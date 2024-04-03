// 322558537 Adi Brafman
package arkanoidGame.display;

import arkanoidGame.gameTools.blocks.DeathBlock;
import arkanoidGame.gameRunTime.BallRemover;
import arkanoidGame.gameRunTime.Counter;
import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.gameTools.Paddle;

import java.awt.Color;

/**
 * The Frame class represents the screen of a game which framed with 4 blocks and a middle one.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class Frame {
    public static final int FRAME_SIZE = 40;
    private int width;
    private int height;
    private Block[] frameBlocks;
    private DeathBlock deathBlock;

    /**
     * Creates a new Frame object with the given dimensions and title.
     *
     * @param width  the width of the screen.
     * @param height the height of the screen.
     */
    public Frame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Creates the screen borders and adds them to the game.
     *
     * @param g       the game to add the borders to.
     * @param counter the Game's Ball's counter.
     */
    public void createFrame(GameLevel g, Counter counter) {
        this.frameBlocks = initializeBlocks();
        for (Block screen : this.frameBlocks) {
            screen.addToGame(g);
        }
        this.deathBlock.addHitListener(new BallRemover(g, counter));
    }

    /**
     * Creates the blocks of the borders of the screen. The bottom block is a DeathBlock object.
     *
     * @return an array of Blocks representing the screen borders.
     */
    private Block[] initializeBlocks() {
        // bottom
        DeathBlock bottom = new DeathBlock(0, this.height, this.width, FRAME_SIZE);
        bottom.setColor(Color.GRAY);
        this.deathBlock = bottom;
        // right
        Block right = new Block(0, 0, FRAME_SIZE, this.height);
        right.setColor(Color.GRAY);
        //left
        Block left = new Block(this.width - FRAME_SIZE, 0, FRAME_SIZE, this.height);
        left.setColor(Color.GRAY);
        // top
        Block top = new Block(0, 0, this.width, FRAME_SIZE);
        top.setColor(Color.GRAY);

        return new Block[]{bottom, right, left, top};
    }

    /**
     * Add the screen blocks into a given Paddle, to keep him in the screen boundaries.
     *
     * @param paddle the paddle to set its boundaries.
     */
    public void stayInScreen(Paddle paddle) {
        for (Block frameBlock : this.frameBlocks) {
            paddle.setBoundaries(frameBlock);
        }
    }

    /**
     * Returns the width of the total screen.
     *
     * @return the width of the screen.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the total screen.
     *
     * @return the height of the screen.
     */
    public int getHeight() {
        return this.height;
    }

}

