// 322558537 Adi Brafman
package arkanoidGame.gameTools.Levels;

import java.util.ArrayList;
import java.util.List;

/**
 * The LevelFactory class is responsible for creating a list of LevelInformation based on
 * the user arguments that provided level indices.
 *
 * @author Adi Brafman
 * adi.brafman@gmail.com
 * @version 1.0
 * @since 11/6/2023
 */
public class LevelFactory {

    /**
     * Creates a list of level configurations based on the provided level indices.
     *
     * @param levelIndex a list of level indices
     * @return a list of LevelInformation
     */
    public List<LevelInformation> getLevels(ArrayList<Integer> levelIndex) {
        ArrayList<LevelInformation> levels = new ArrayList<>();

        for (int level : levelIndex) {
            switch (level) {
                case 1 -> levels.add(new DirectHitLevel());
                case 2 -> levels.add(new WideEasyLevel());
                case 3 -> levels.add(new Green3Level());
                default -> {
                    continue;
                }
            }
        }
        return levels;
    }

}
