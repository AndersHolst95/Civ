package hotciv.framework.victoryCondition;

import hotciv.framework.Player;

public interface VictoryStrategy {
    boolean checkVictory(int age, Player player);
}

