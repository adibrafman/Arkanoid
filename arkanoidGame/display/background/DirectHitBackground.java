// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.shapes.Point;

import java.awt.Color;
import java.util.LinkedList;

/**
 * Creating the background for the "Direct Hit" level.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class DirectHitBackground {

    private int width;
    private int height;
    private Target target;

    /**
     * Constructs a DirectHitBackground object with the specified width and height.
     *
     * @param width  the width of the background
     * @param height the height of the background
     */
    public DirectHitBackground(int width, int height) {
        this.width = width;
        this.height = height;
        this.target = new Target(new Point(width / 2, height / 4), (int) (this.width / 2.5));
    }

    /**
     * Create background.
     *
     * @return the background
     */
    public Background create() {
        LinkedList<BackgroundObject> backgroundObjects = new LinkedList<>();
        backgroundObjects.add(this.target);
        return new Background(width, height, Color.BLACK, backgroundObjects);
    }
}
