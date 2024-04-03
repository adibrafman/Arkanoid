// 322558537 Adi Brafman
package arkanoidGame.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of Sprites to manage their drawing and movement timing.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 7/5/2023
 */
public class SpriteCollection {
    private LinkedList<Sprite> sprites;

    /**
     * Constructs an empty SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<Sprite>();
    }

    /**
     * Adds a Sprite to the collection.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    public void addSprite(LinkedList<Sprite> s){
        this.sprites.addAll(s);
    }

    /**
     * Call timePassed() on all sprites in this collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<>(this.sprites);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * call the method drawOn(d) in all the sprites in the collection, and pass them the given drawSurface.
     *
     * @param d surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteList = new ArrayList<>(this.sprites);
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * Remove a Sprite from the 'sprites' list.
     * @param s
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
