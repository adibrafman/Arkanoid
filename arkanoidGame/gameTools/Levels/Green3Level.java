// 322558537 Adi Brafman
package arkanoidGame.gameTools.Levels;

import arkanoidGame.display.Frame;
import arkanoidGame.display.background.Background;
import arkanoidGame.display.background.Green3Background;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.movement.Velocity;
import arkanoidGame.sprites.Sprite;
import arkanoidGame.shapes.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Green3Level class represents the "Green 3" level in the Arkanoid game.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Green3Level implements LevelInformation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BLOCK_WIDTH = 48;
    private static final int BLOCK_HEIGHT = 20; // the height of each block in the game
    private static final int BALL_SPEED = 7;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 100;

    private List<Block> blocks;
    private Background background;

    /**
     * Creates a new Green3Level instance.
     */
    public Green3Level() {
        this.blocks = new LinkedList<>();
        Green3Background d = new Green3Background(WIDTH, HEIGHT);
        this.background = d.create();

        this.createLevelBlocks(new Color[]{Color.WHITE, Color.BLUE, Color.YELLOW, Color.RED, Color.GRAY});
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        LinkedList<Velocity> ballVelocities = new LinkedList<>();
        double ballVelocitySpace = 90.0 / this.numberOfBalls();
        double[] spaces = {-ballVelocitySpace, ballVelocitySpace};
        double angle;

        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle = 0 + (spaces[(int) Math.floor(i / ((double) numberOfBalls() / 2))])
                    * (i % ((double) numberOfBalls() / 2) + 1);
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, BALL_SPEED));
        }
        return ballVelocities;
    }


    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Green 3";
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
        return 40;
    }

    private void createBlock(Point upperLeftPoint, Color color) {
        Block block = new Block(upperLeftPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
        block.setColor(color);
        blocks.add(block);
    }

    private void createLevelBlocks(Color[] colors) {
        LinkedList<Integer> blocksRow = numInRow(this.numberOfBlocksToRemove());
        int blocksY = HEIGHT / 2 - Frame.FRAME_SIZE;

        for (int i = 0; i < blocksRow.size(); i++) {
            int blockStart = WIDTH - Frame.FRAME_SIZE - blocksRow.get(i) * BLOCK_WIDTH;
            Color rowColor = colors[i % colors.length];
            for (int j = 0; j < blocksRow.get(i); j++) {
                createBlock(new Point(blockStart, blocksY), rowColor);
                blockStart += BLOCK_WIDTH;
            }
            blocksY -= BLOCK_HEIGHT;
        }
    }

    /**
     * Calculates how many blocks to include in each row, based on the total number of blocks.
     *
     * @param blocksNum the blocks num
     * @return a list of numbers that representing how many blocks there can be on any row
     */
    public LinkedList<Integer> numInRow(int blocksNum) {
        LinkedList<Integer> numInRow = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i <= blocksNum / 2; i++) {
            sum += i;
            numInRow.add(i);
            if (sum > blocksNum) {
                numInRow.remove(i);
                sum -= i;
                break;
            }
        }
        if (sum != blocksNum) {
            int lefters = blocksNum - sum;
            while (lefters != 0) {
                while (lefters < numInRow.size()) {
                    lefters += numInRow.get(0);
                    numInRow.remove(0);
                }
                numInRow.replaceAll(integer -> integer + 1);
                lefters -= numInRow.size();
            }
        }
        return numInRow;
    }
}
