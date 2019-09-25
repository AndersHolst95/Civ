package hotciv.framework.victoryStrategy;

import hotciv.framework.GameVariables;
import hotciv.framework.Player;

public class ZetaVictory implements VictoryStrategy {
    private VictoryStrategy conquest = new ConquestVictory();
    private VictoryStrategy combat = new ThreeCombatVictories();

    @Override
    public boolean checkVictory(Player player) {
        if (GameVariables.round < 19)
            return conquest.checkVictory(player);

        else if (GameVariables.round == 19) {
            GameVariables.resetVictories();
            return conquest.checkVictory(player);
        }
        else
            return combat.checkVictory(player);
    }
}

