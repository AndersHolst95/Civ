package hotciv.framework.victoryStrategy;
import hotciv.framework.*;

public class TimeVictory implements VictoryStrategy {
    public boolean checkVictory(int age, Player player){
        return age == -3000 && player.equals(Player.RED);
    }
}
