// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.shapes.Point;

import java.awt.Color;
import java.util.LinkedList;

/**
 * Creating the background for the "Green 3" level.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Green3Background {
    private final double WIDTH_RATIO = 0.1125;
    private final double HEIGHT_RATIO = 0.25;

    private int width;
    private int height;
    private Building building;
    private Antenna antenna;


    /**
     * Constructs a Green3Background object with the specified width and height.
     *
     * @param width  the width of the background
     * @param height the height of the background
     */
    public Green3Background(int width, int height) {
        this.width = width;
        this.height = height;
        this.building = new Building(new Point(this.width / 8, (this.height * 3) / 4), (int) (this.width * WIDTH_RATIO), (int) (this.height * HEIGHT_RATIO));
        this.antenna = new Antenna(this.building.getWall(), (this.height * 2) / 3);
    }


    /**
     * Creates and returns the background for the "Green 3" level.
     *
     * @return the created background
     */
    public Background create() {
        LinkedList<BackgroundObject> backgroundObjects = new LinkedList<>();
        backgroundObjects.add(this.building);
        backgroundObjects.add(this.antenna);
        return new Background(this.width, this.height, Color.GREEN.darker(), backgroundObjects);
    }


}
