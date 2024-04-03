import arkanoidGame.display.animation.AnimationRunner;
import arkanoidGame.gameRunTime.GameFlow;
import arkanoidGame.gameTools.Levels.LevelInformation;
import arkanoidGame.gameTools.Levels.LevelFactory;
import arkanoidGame.gameRunTime.exception.LevelFormatException;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ass6Game {
    public static void main(String[] args) {
        ArrayList<Integer> numOfLevel = new ArrayList<>();
        for (String level : args) {
            try {
                int levelIndex = Integer.parseInt(level);
                if (levelIndex < 1 || levelIndex > 3) {
                    throw new LevelFormatException("InValid level Index");
                }
                numOfLevel.add(levelIndex);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        if (numOfLevel.isEmpty()) {
            numOfLevel.addAll(Arrays.asList(1, 2, 3));
        }
        List<LevelInformation> levels = new LevelFactory().getLevels(numOfLevel);

        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(levels);
    }
}
