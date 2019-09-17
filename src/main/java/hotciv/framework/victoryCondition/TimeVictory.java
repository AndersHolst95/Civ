package hotciv.framework.victoryCondition;

import hotciv.framework.Player;

public class TimeVictory implements VictoryStrategy {
    public boolean checkVictory(int age, Player player){
        return age == -3000 && player.equals(Player.RED);
    }
}
