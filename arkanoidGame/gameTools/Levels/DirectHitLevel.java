// 322558537 Adi Brafman
package arkanoidGame.gameTools.Levels;

import arkanoidGame.display.background.Background;
import arkanoidGame.display.background.DirectHitBackground;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.movement.Velocity;
import arkanoidGame.sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The DirectHitLevel class represents the "Direct Hit" level in the Arkanoid game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class DirectHitLevel implements LevelInformation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private List<Velocity> ballsVelocity;
    private List<Block> blocks;
    private Background background;

    /**
     * Creates a new DirectHitLevel instance.
     */
    public DirectHitLevel() {
        this.ballsVelocity = new LinkedList<>();
        this.blocks = new LinkedList<>();
        this.background = new DirectHitBackground(WIDTH, HEIGHT).create();
    }


    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.ballsVelocity.add(new Velocity(0, -15));
        return this.ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block((double) (WIDTH / 2) - 15, (double) (HEIGHT / 4) - 15,
                30, 30);
        b.setColor(Color.RED);
        this.blocks.add(b);
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
