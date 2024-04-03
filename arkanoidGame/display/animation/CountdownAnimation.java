// 322558537 Adi Brafman
package arkanoidGame.display.animation;

import biuoop.DrawSurface;
import arkanoidGame.sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation class represents an animation that displays a countdown on top of a game screen.
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */

public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private int countFrom;
    private double frameAppearTime;
    private double currentAppearTime;
    private long startCount;
    private boolean stop;

    /**
     * Constructs a CountdownAnimation with the specified duration, starting count, and game screen.
     *
     * @param numOfSeconds the total duration of the countdown animation in seconds
     * @param countFrom    the starting count value
     * @param gameScreen   the SpriteCollection representing the game screen to display underneath the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.stop = false;
        this.frameAppearTime = (numOfSeconds / countFrom) * 1000;
        this.currentAppearTime = frameAppearTime;
        this.countFrom = countFrom;
        this.startCount = System.currentTimeMillis(); // timing
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.calculateTime();
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(this.countFrom), 50);
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom == 0) {
            this.stop = true;
        }
        return this.stop;
    }

    /**
     * Calculates the current time and updates the countdown number if necessary.
     */
    private void calculateTime() {
        if (System.currentTimeMillis() - startCount >= this.frameAppearTime) {
            this.countFrom--;
            this.startCount = System.currentTimeMillis();
        }
    }
}
