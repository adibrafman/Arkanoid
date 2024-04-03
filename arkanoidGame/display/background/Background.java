// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.gameTools.blocks.Block;
import arkanoidGame.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * The Background class represents the background of a game level.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Background implements Sprite {

    private Block baseBackground;
    private int width;
    private int height;
    private List<BackgroundObject> objects;

    /**
     * Instantiates a new Background for a game level.
     *
     * @param width     the width of the background
     * @param height    the height of the background
     * @param baseColor the base color of the background
     * @param objects   the background objects to be displayed
     */
    public Background(int width, int height, Color baseColor, List<BackgroundObject> objects) {
        this.baseBackground = new Block(0, 0, width, height);
        this.baseBackground.setColor(baseColor);
        this.width = width;
        this.height = height;
        this.objects = objects;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.baseBackground.drawOn(d);
        if (objects != null) {
            for (BackgroundObject object : this.objects) {
                object.drawOn(d);
            }
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
