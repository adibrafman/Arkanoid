// 322558537 Adi Brafman
package arkanoidGame.display;

import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.gameRunTime.Counter;
import arkanoidGame.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A Sprite that in charge of displaying the current score.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Creates a new ScoreIndicator object with the game's score counter.
     * @param score the score counter to be displayed
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, d.getWidth(), 20);
        d.drawText(d.getWidth() / 2 - 10, 16, "Score: " + score.getValue(), 13);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
