package hotciv.framework.victoryStrategy;

import hotciv.framework.GameVariables;
import hotciv.framework.Player;

public class ThreeCombatVictories implements VictoryStrategy {
    private int zeroPoint = 0;

    @Override
    public boolean checkVictory(Player player) {
        return GameVariables.getCombatVictories(player) >= 3 + zeroPoint;
    }

    public void setZeroPoint(int i){zeroPoint = i;}
}


