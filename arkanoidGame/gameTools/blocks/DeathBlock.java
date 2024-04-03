// 322558537 Adi Brafman
package arkanoidGame.gameTools.blocks;

import arkanoidGame.shapes.Rectangle;
import biuoop.DrawSurface;

/**
 * The DeathBlock class represents a block that causes the player to lose a life upon collision.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class DeathBlock extends Block {
    /**
     * Creates a new DeathBlock instance based on the provided rectangle.
     *
     * @param block the rectangle with the sizes and the location of the block.
     */
    public DeathBlock(Rectangle block) {
        super(block);
    }

    /**
     * Creates a new DeathBlock instance with the specified position, width, and height.
     *
     * @param x      the x-coordinate of the block's position.
     * @param y      the y-coordinate of the block's position.
     * @param width  the width of the block.
     * @param height the height of the block.
     */
    public DeathBlock(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void drawOn(DrawSurface d) {
    }

}
