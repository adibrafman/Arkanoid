// 322558537 Adi Brafman
package arkanoidGame.display;

import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The name of a game level.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Create a new LevelName object.
     *
     * @param name the name of the game level
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() * 2) / 3, 16, this.name, 13);
    }
    @Override
    public void timePassed() {

    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
