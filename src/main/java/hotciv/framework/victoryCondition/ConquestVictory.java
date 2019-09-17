package hotciv.framework.victoryCondition;

import hotciv.framework.Player;

public class ConquestVictory implements VictoryStrategy {
    public boolean checkVictory(int age, Player player){
        return true;
    };
}
