package hotciv.standard.victoryStrategy;
import hotciv.framework.*;

public class TimeVictory implements VictoryStrategy {
    public boolean checkVictory(Player player){
        return GameVariables.age == -3000 && player.equals(Player.RED);
    }
}
