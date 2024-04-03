// 322558537 Adi Brafman
package arkanoidGame.Listeners;

/**
 * The HitNotifier interface represents an object that can notify listeners about hit events.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 26/05/2023
 */
public interface HitNotifier {
    /**
     * Add HitListener as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove HitListener from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
