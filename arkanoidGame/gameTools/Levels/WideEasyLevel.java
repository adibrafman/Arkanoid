// 322558537 Adi Brafman
package arkanoidGame.gameTools.Levels;

import arkanoidGame.display.Frame;
import arkanoidGame.display.background.Background;
import arkanoidGame.display.background.WideEasyBackground;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.movement.Velocity;
import arkanoidGame.sprites.Sprite;

import java.util.LinkedList;
import java.util.List;

import java.awt.Color;

/**
 * The WideEasyLevel class represents the level "Wide Easy" in the Arkanoid game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class WideEasyLevel implements LevelInformation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BLOCK_WIDTH = 48;

    private List<Block> blocks;
    private Background background;

    /**
     * Creates a new WideEasyLevel instance.
     */
    public WideEasyLevel() {
        this.blocks = new LinkedList<>();
        WideEasyBackground d = new WideEasyBackground(WIDTH, HEIGHT);
        this.background = d.create();

        this.createBlocks();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        LinkedList<Velocity> ballVelocities = new LinkedList<>();
        double ballVelocitySpace = 90.0 / this.numberOfBalls();
        double[] spaces = {-ballVelocitySpace, ballVelocitySpace};
        int speed = 7;
        double angle;

        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle = 0 + (spaces[(int) Math.floor(i / ((double) numberOfBalls() / 2))])
                    * (i % ((double) numberOfBalls() / 2) + 1);
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }

        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    private void createBlocks() {
        double y = ((double) HEIGHT / 12) * 5;
        Color[] colors = this.colors();
        int upperLeftPoint = Frame.FRAME_SIZE;
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Block block = new Block(upperLeftPoint, y, BLOCK_WIDTH, BLOCK_HEIGHT);
            block.setColor(colors[i % colors.length]);
            this.blocks.add(block);
            upperLeftPoint += BLOCK_WIDTH;
        }
    }

    private Color[] colors() {
        return new Color[]{Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE,
                Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};
    }
}
