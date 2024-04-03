// 322558537 Adi Brafman
package arkanoidGame.gameRunTime.exception;

/**
 * The LevelFormatException class represents an exception that is thrown when there is a format
 * error in argument for the level index.
 * It extends the NumberFormatException class, which is a subclass of RuntimeException.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class LevelFormatException extends NumberFormatException {
    /**
     * Constructs a new LevelFormatException with the specified error message.
     *
     * @param message the error message associated with the exception
     */
    public LevelFormatException(String message) {
        super(message);
    }
}
