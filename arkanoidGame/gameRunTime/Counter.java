// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

/**
 * Counter that can be increased, decreased, and queried for its current value.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 26/05/2023
 */
public class Counter {
    private int number;

    /**
     * Create a new Counter object with the specified initial value.
     *
     * @param number the counter's initial value
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * Creates a new Counter object with an initial value of 0.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number to add.
     */
    public void increase(int number) {
        this.number += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.number -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the counter current value
     */
// get current count.
    public int getValue() {
        return this.number;
    }
}
