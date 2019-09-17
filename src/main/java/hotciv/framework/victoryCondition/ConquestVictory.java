package hotciv.framework.victoryCondition;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.World;

public class ConquestVictory implements VictoryStrategy {
    public boolean checkVictory(int age, Player player){
        // If a player owns all of the cities, the player wins

        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                if(World.getCityAt(new Position(i, j)) != null){
                    if(World.getCityAt(new Position(i, j)).getOwner() != player)
                        return false;
                }
            }
        }
        return true;
    }
}
