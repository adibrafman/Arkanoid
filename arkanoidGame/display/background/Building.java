// 322558537 Adi Brafman
package arkanoidGame.display.background;

import arkanoidGame.shapes.Point;
import arkanoidGame.shapes.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The Building class represents a building object that can be drawn on a background.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class Building implements BackgroundObject {

    private static final int WINDOWS_ROW = 5;
    private static final int WINDOW_COLUMN = 6;
    private List<Rectangle> windows;
    private Rectangle wall;

    /**
     * Constructs a Building object with the specified upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the building
     * @param width     the width of the building
     * @param height    the height of the building
     */
    public Building(Point upperLeft, int width, int height) {
        this.wall = new Rectangle(upperLeft, width, height);
        this.windows = new LinkedList<>();
        this.createWindows();

    }

    private void createWindows() {

        int windowHeight = (int) (4 * this.wall.getHeight() / (5 * WINDOW_COLUMN));
        int windowWidth = windowHeight / 2;

        int availableWidth = (int) (wall.getWidth() - (WINDOWS_ROW * windowWidth));
        int availableHeight = (int) (wall.getHeight() - (WINDOW_COLUMN * windowHeight));

        int horizontalSpacing = (availableWidth + 2) / (WINDOWS_ROW + 1);
        int verticalSpacing = availableHeight / (WINDOW_COLUMN - 1);

        // Create Window objects
        for (int row = 0; row < WINDOWS_ROW; row++) {
            for (int col = 0; col < WINDOW_COLUMN; col++) {
                int x = (int) (wall.getUpperLeft().getX() + horizontalSpacing + (row * (windowWidth + horizontalSpacing)));
                int y = (int) (wall.getUpperLeft().getY() + verticalSpacing + (col * (windowHeight + verticalSpacing)));
                // Create Window object with coordinates (x, y), width windowWidth, and height windowHeight
                Rectangle window = new Rectangle(x, y, windowWidth, windowHeight);
                this.windows.add(window);
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) wall.getUpperLeft().getX(), (int) wall.getUpperLeft().getY(),
                (int) wall.getWidth(), (int) wall.getHeight());

        d.setColor(Color.WHITE);
        for (Rectangle window : windows) {
            d.fillRectangle((int) window.getUpperLeft().getX(), (int) window.getUpperLeft().getY(),
                    (int) window.getWidth(), (int) window.getHeight());
        }
    }

    /**
     * Returns a copy of the building's wall rectangle.
     *
     * @return a copy of the wall rectangle
     */
    public Rectangle getWall() {
        return new Rectangle(wall.getUpperLeft(), wall.getWidth(), wall.getHeight());
    }
}
