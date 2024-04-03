// 322558537 Adi Brafman
package arkanoidGame.gameTools;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import arkanoidGame.shapes.Rectangle;
import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Line;
import arkanoidGame.collisionDetection.Collidable;
import arkanoidGame.collisionDetection.CollisionInfo;
import arkanoidGame.collisionDetection.GameEnvironment;
import arkanoidGame.sprites.Sprite;
import arkanoidGame.movement.Velocity;

/**
 * The `Paddle` class represents a rectangular paddle that can move horizontally.
 * The class implements both the `Sprite` and `Collidable` interfaces.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class Paddle implements Sprite, Collidable {
    private static final int STEP = 10;
    private static final double EPSILON = 0.000001;
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 20;
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private final Color color;
    private List<Ball> balls;
    private GameEnvironment environment;
    private int step;

    /**
     * Constructs a new paddle with the given rectangle shape and keyboard sensor.
     *
     * @param paddle   the rectangle shape of the Paddle
     * @param keyboard the keyboard sensor used to control the paddle's movement
     */
    public Paddle(Rectangle paddle, KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.color = Color.YELLOW;
        this.balls = new ArrayList<>();
        this.step = STEP;

        this.environment = new GameEnvironment();
    }

    /**
     * Constructs a new paddle with the given upper left point, width, height, and keyboard sensor.
     *
     * @param upperLeft the upper left point of the paddle
     * @param width     the width of the paddle
     * @param height    the height of the paddle
     * @param keyboard  the keyboard sensor used to control the paddle's movement
     */
    public Paddle(Point upperLeft, double width, double height, KeyboardSensor keyboard) {
        this(new Rectangle(upperLeft, width, height), keyboard);
    }

    /**
     * Constructs a new paddle with a given x and y coordinate, width, height, and keyboard sensor.
     *
     * @param x        the x coordinate of the paddle
     * @param y        the y coordinate of the paddle
     * @param width    the width of the paddle
     * @param height   the height of the paddle
     * @param keyboard the keyboard sensor used to control the paddle's movement
     */
    public Paddle(double x, double y, double width, double height, KeyboardSensor keyboard) {
        this(new Rectangle(x, y, width, height), keyboard);
    }

    /**
     * Constructs a new paddle with a default values.
     *
     * @param keyboard the keyboard sensor used to control the paddle's movement
     */
    public Paddle(KeyboardSensor keyboard) {
        this(new Rectangle(10, 500, DEFAULT_WIDTH, DEFAULT_HEIGHT), keyboard);
    }

    /**
     * Constructs a new paddle object.
     *
     * @param x        the x coordinate of the paddle
     * @param y        the y coordinate of the paddle
     * @param width    the width of the paddle
     * @param keyboard the keyboard sensor used to control the paddle's movement
     * @param step the size of one step of the paddle
     */
    public Paddle(double x, double y, double width, KeyboardSensor keyboard, int step) {
        this(new Rectangle(x, y, width, DEFAULT_HEIGHT), keyboard);
        this.step = step;
    }

    /**
     * Sets the boundaries of the paddle, with a given block which represent one of the walls of game screen.
     *
     * @param collidable a block that representing a wall of game screen.
     */
    public void setBoundaries(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * Moves the paddle one step to the left, and keep him in the game screen by checking for collision
     * with the walls that represented by the gameEnvironment collection.
     */
    public void moveLeft() {
        double currX = this.paddle.getUpperLeft().getX();
        double currY = this.paddle.getUpperLeft().getY();
        double stepMove = this.paddle.getWidth() - this.step;

        Line trajectory = new Line(currX - this.step, currY, currX + stepMove, currY);
        CollisionInfo collide = environment.getClosestCollision(trajectory);

        // The paddle reached the left border of the screen
        if (collide != null) {
            this.paddle = new Rectangle(collide.collisionPoint().getX(), currY,
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(currX - this.step, currY, this.paddle.getWidth(), this.paddle.getHeight());
        }

    }

    /**
     * Moves the paddle one step to the right, and keep him in the game screen by checking for collision
     * with the walls that represented by the gameEnvironment collection.
     */
    public void moveRight() {
        double currX = this.paddle.getUpperLeft().getX();
        double currY = this.paddle.getUpperLeft().getY();
        double stepMove = this.step + this.paddle.getWidth();

        Line trajectory = new Line(currX + this.step, currY, currX + stepMove, currY);
        CollisionInfo collide = environment.getClosestCollision(trajectory);

        // The paddle reached the right border of the screen
        if (collide != null) {
            this.paddle = new Rectangle(collide.collisionPoint().getX() - this.paddle.getWidth(), currY,
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(currX + this.step, currY, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Check if by moving the paddle one step, makes him to imprison the game's balls inside him. if so - push the
     * ball outside from the paddle.
     *
     * @param border  x coordinate of the paddle's vertical side which decided in the side of the step
     *                (left or right). If the user pressed on the left key - the collision with a ball will be
     *                in the paddle's left side. so the ball will move there.
     * @param epsilon a small number to add to the future gap between the ball's location and the paddle's
     */
    private void ifPushing(double border, double epsilon) {
        for (Ball ball : balls) {
            if (this.paddle.isIncludePoint(new Point(ball.getX(), ball.getY()))) {
                Velocity v = this.hit(ball, new Point(border, ball.getY()), ball.getVelocity());
                ball.pushed(border + epsilon, this.paddle.getUpperLeft().getY(), v);
            }
        }
    }

    /**
     * Add a ball from a game to the paddle list, so the paddle will recognize him while he's moving, and
     * will not collide him inside him.
     *
     * @param ball a ball from the game that needed to be added to the paddle's list
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    // Sprite
    @Override
    public void timePassed() {
        // Check for key presses and move the paddle accordingly
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            ifPushing(this.paddle.getUpperLeft().getX(), 0.5);
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            ifPushing(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(), -0.5);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) Math.round(this.paddle.getUpperLeft().getX());
        int y = (int) this.paddle.getUpperLeft().getY();
        int width = (int) Math.round(this.paddle.getWidth());
        int height = (int) Math.round(this.paddle.getHeight());

        // Set the color of the rectangle
        d.setColor(this.color);
        // draw the Rectangle
        d.fillRectangle(x, y, width, height);

        // draw the outLines
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddle.getUpperLeft(), this.paddle.getWidth(), this.paddle.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // find in which region of the paddle the object hit
        int region = whichRegion(collisionPoint);

        // return a velocity that is calculated according to the region hit
        return findVelocityAccordingToRegion(region, currentVelocity);
    }

    /**
     * Calculate in which region(1 - most left, 5 - most right) of the paddle the collision occurred.
     * <p>
     * vertical left wall - region 1
     * vertical right wall - region 5
     * horizontal bottom wall - region 3
     * horizontal upper wall - calculates in which region the hit occurred
     * </p>
     *
     * @param collision the collision point
     * @return the region of the collision
     */
    private int whichRegion(Point collision) {
        int regionNum = 5;
        int regionSize = (int) Math.round(this.paddle.getWidth()) / regionNum;
        double topY = this.paddle.getUpperLeft().getY();
        double leftX = this.paddle.getUpperLeft().getX();

        if (this.thershold(leftX, collision.getX())) {
            return 1;
            // collision with the right vertical wall of the paddle
        } else if (this.thershold(leftX + this.paddle.getWidth(), collision.getX())) {
            return 5;
            // if the collision is in the bottom side of the paddle
        } else if (this.thershold(collision.getY(), this.paddle.getHeight() + topY)) {
            return 3;
        } else {
            return (int) (((collision.getX() - (int) this.paddle.getUpperLeft().getX()) / regionSize) + 1);
        }

    }

    private Velocity findVelocityAccordingToRegion(int region, Velocity currentVelocity) {
        // Pythagoras sentence
        double vSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        return switch (region) {
            case 1 -> Velocity.fromAngleAndSpeed(300, vSpeed);
            case 2 -> Velocity.fromAngleAndSpeed(330, vSpeed);
            case 3 -> new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4 -> Velocity.fromAngleAndSpeed(30, vSpeed);
            case 5 -> Velocity.fromAngleAndSpeed(60, vSpeed);
            default -> new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        };
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.getScreen().stayInScreen(this);
        g.addSprite(this);
        g.addCollidable(this);
    }

    private boolean thershold(double num1, double num2) {
        return Math.abs(num1 - num2) < EPSILON;
    }
}

