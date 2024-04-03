// 322558537 Adi Brafman
package arkanoidGame.gameTools;

import arkanoidGame.display.LevelName;
import arkanoidGame.display.ScoreIndicator;
import arkanoidGame.display.animation.KeyPressStoppableAnimation;
import arkanoidGame.gameRunTime.LevelTrackingListener;
import arkanoidGame.gameRunTime.ScoreTrackingListener;
import arkanoidGame.gameTools.Levels.LevelInformation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import arkanoidGame.collisionDetection.GameEnvironment;
import arkanoidGame.collisionDetection.Collidable;
import arkanoidGame.sprites.SpriteCollection;
import arkanoidGame.sprites.Sprite;
import arkanoidGame.gameRunTime.Counter;
import arkanoidGame.gameRunTime.BlockRemover;
import arkanoidGame.display.Frame;
import arkanoidGame.display.PauseScreen;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.display.animation.Animation;
import arkanoidGame.display.animation.AnimationRunner;
import arkanoidGame.display.animation.CountdownAnimation;
import arkanoidGame.shapes.Point;

/**
 * A game of a bouncing balls, moving paddle and blocks.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class GameLevel implements Animation {
    private static final int BLOCK_HEIGHT = 20; // the height of each block in the game
    private LevelInformation info;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Frame frame;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;


    /**
     * Initializes a new GameLevel object with the specified level information, keyboard sensor,
     * animation runner, score counter, and frame.
     *
     * @param info   the level information containing details about the game level.
     * @param ks     the keyboard sensor used for input.
     * @param runner the animation runner responsible for running the game animation.
     * @param score  the counter for keeping track of the player's score.
     * @param frame  the frame object representing the game screen.
     */
    public GameLevel(LevelInformation info, KeyboardSensor ks, AnimationRunner runner, Counter score, Frame frame) {
        this.info = info;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(info.blocks().size());
        this.ballCounter = new Counter(info.numberOfBalls());
        this.score = score;
        this.runner = runner;
        this.keyboard = ks;
        this.frame = frame;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object that will be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object (e.g. a ball) to the game sprites.
     *
     * @param s the sprite object that will be added to the game sprites.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    private Point initialPoint() {
        double x = ((double) frame.getWidth() / 2) - ((double) info.paddleWidth() / 2);
        double y = this.frame.getHeight() - Frame.FRAME_SIZE - BLOCK_HEIGHT;
        return new Point(x, y);
    }

    private Paddle createPaddle() {
        Point p = initialPoint();
        Paddle paddle = new Paddle(p.getX(), p.getY(), info.paddleWidth(), keyboard, info.paddleSpeed());
        paddle.addToGame(this);
        return paddle;
    }

    private void initilizeBlocks(ScoreTrackingListener scoreTrack, LevelTrackingListener levelTrack) {
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        for (Block levelBlock : this.info.blocks()) {
            levelBlock.addToGame(this);
            levelBlock.addHitListener(blockRemover);
            levelBlock.addHitListener(scoreTrack);
            levelBlock.addHitListener(levelTrack);
        }
    }

    /**
     * Creates a specified number of ball objects at random locations and adds them to the game.
     *
     * @param paddle the paddle of the game that have a list of balls he keeps, so he won't include them inside him.
     */
    private void createBalls(Paddle paddle) {
        double epsilon = 0.5;
        for (int i = 0; i < info.numberOfBalls(); i++) {
            Ball ball = new Ball(frame.getWidth() / 2, initialPoint().getY() - 5, 4, Color.WHITE);
            ball.setVelocity(info.initialBallVelocities().get(i)); // use the default values for the ball's velocity
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
            paddle.addBall(ball);
        }
    }

    private void createName() {
        LevelName name = new LevelName("Level Name: " + this.info.levelName());
        name.addToGame(this);
    }


    /**
     * Creates and initializes all the game objects and adds them to the game.
     *
     * @param levelTrack the level tracking listener for updating the current level.
     */
    public void initialize(LevelTrackingListener levelTrack) {
        levelTrack.newLevel(this.ballCounter);
        this.info.getBackground().addToGame(this);
        this.frame.createFrame(this, this.ballCounter);
        Paddle paddle = createPaddle();
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        createName();
        initilizeBlocks(scoreTrack, levelTrack);
        createBalls(paddle);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;

        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
        }
        System.out.println(this.score.getValue());
    }

    /**
     * Get the frame of the level.
     *
     * @return the frame of the level
     */
    public Frame getScreen() {
        return this.frame;
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        environment.removeFromEnvironment(c);
    }

    /**
     * Removes a sprite object from the sprite collection.
     * @param s the sprite object to be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        // stopping condition
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
        }
        return !this.running;
    }
}
