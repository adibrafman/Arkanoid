// 322558537 Adi Brafman
package arkanoidGame.gameRunTime;

import arkanoidGame.display.Frame;
import arkanoidGame.display.animation.AnimationRunner;
import arkanoidGame.display.animation.KeyPressStoppableAnimation;
import arkanoidGame.gameTools.GameLevel;
import arkanoidGame.gameTools.Levels.LevelInformation;

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class manages the flow of the game, including running the levels and
 * handling user input.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;
    private Frame frame;

    /**
     * Creates a new GameFlow instance.
     *
     * @param ar  the AnimationRunner responsible for running the animations
     * @param ks  the KeyboardSensor for detecting user input
     * @param gui the GUI object representing the game window
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.frame = new Frame(800, 600);
        this.score = new Counter(0);
    }

    /**
     * Run the levels of the game.
     *
     * @param levels the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        LevelTrackingListener levelTrack = new LevelTrackingListener(levels, score);
        int i = 0;
        do {
            GameLevel level = new GameLevel(levels.get(i), this.ks, this.ar, this.score, this.frame);
            level.initialize(levelTrack);
            level.run();
            i++;
        } while (!levelTrack.shouldStop() && i < levels.size());
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                levelTrack.getEndGameCondition()));
        this.gui.close();
    }
}
