package hotciv.framework.victoryStrategy;

import hotciv.framework.GameVariables;
import hotciv.framework.Player;

import java.util.HashMap;

public class ZetaVictory implements VictoryStrategy {
    private ConquestVictory conquest = new ConquestVictory();
    private ThreeCombatVictories combat = new ThreeCombatVictories();
    private HashMap<Player, Integer> winsAtRound19;

    @Override
    public boolean checkVictory(Player player) {
        if (GameVariables.round < 19)
            return conquest.checkVictory(player);

        else if (GameVariables.round == 19) {
            winsAtRound19 = (HashMap<Player, Integer>) GameVariables.combatVictories.clone();
            return conquest.checkVictory(player);
        }
        else {
            combat.setZeroPoint(winsAtRound19.get(player));
            return combat.checkVictory(player);
        }
    }
}

