package hotciv.framework.victoryStrategy;

import hotciv.framework.GameVariables;
import hotciv.framework.Player;

public class ThreeCombatVictories implements VictoryStrategy {

    @Override
    public boolean checkVictory(Player player) {
        return GameVariables.getCombatVictories(player) >= 3;
    }
}
