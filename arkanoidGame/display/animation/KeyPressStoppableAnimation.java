// 322558537 Adi Brafman
package arkanoidGame.display.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *Animation that can be stopped by pressing a specific key.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean keyPressed;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation with the specified keyboard sensor, key,
     * and animation.
     *
     * @param sensor    the sensor used to detect key presses
     * @param key       the key in the keyboard that will stop the animation
     * @param animation the Animation object to be stopped
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.keyPressed = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        if (isAlreadyPressed && !sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }

        if (!isAlreadyPressed && this.sensor.isPressed(this.key)) {
            this.keyPressed = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.keyPressed || this.animation.shouldStop();
    }
}
