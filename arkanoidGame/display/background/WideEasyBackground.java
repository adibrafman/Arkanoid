// 322558537 Adi Brafman
package arkanoidGame.display.background;

import java.awt.Color;
import java.util.LinkedList;

/**
 * The background of the Wide easy level.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class WideEasyBackground {
    private int width;
    private int height;
    private Sun sun;


    /**
     * The WideEasyBackground class responsible for creating the background for the WideEasyLevel.
     *
     * @param width  the width of the background
     * @param height the height of the background
     */
    public WideEasyBackground(int width, int height) {
        this.width = width;
        this.height = height;
        this.sun = new Sun(this.width, (this.height / 12) * 5);
    }

    /**
     * Create the background for the WideEasyLevel.
     *
     * @return the background
     */
    public Background create() {
        LinkedList<BackgroundObject> backgroundObjects = new LinkedList<>();
        backgroundObjects.add(this.sun);
        return new Background(width, height, Color.WHITE, backgroundObjects);
    }
}
