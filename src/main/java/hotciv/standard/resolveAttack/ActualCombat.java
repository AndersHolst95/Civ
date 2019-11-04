package hotciv.standard.resolveAttack;

import hotciv.framework.*;
import hotciv.framework.Position;
import hotciv.framework.World;
import hotciv.standard.random.RandomStrategy;
import hotciv.standard.random.DieRoll;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;

public class ActualCombat implements  ResolveAttackStrategy{
    RandomStrategy rand;

    public ActualCombat(RandomStrategy rand){
       this.rand = rand;
    }
    public ActualCombat(){
        this.rand = new DieRoll();
    }

    @Override
    public boolean unitAttack(Position attPos, Position defPos) {
        // Husk at ændre antal vundre kampe for vinderen
        UnitImpl attacker = (UnitImpl) World.getUnitAt(attPos);
        UnitImpl defender = (UnitImpl) World.getUnitAt(defPos);

        // TERRAIN BONUS
        int attBonus;
        if (World.getCityAt(attPos) == null) // if there is not a city at the attacker..
            attBonus = GameConstants.TILE.toClass(World.getTileAt(attPos).getTypeString()).getCombatBonus(); // use the terrain bonus
        else // use the city bonus
            attBonus = GameConstants.TILE.CITY_COMBAT_BONUS;

        int defBonus;
        if (World.getCityAt(defPos) == null) // if there is not a city at the defender..
            defBonus = GameConstants.TILE.toClass(World.getTileAt(defPos).getTypeString()).getCombatBonus(); // use the terrain bonus
        else // use the city bonus
            defBonus = GameConstants.TILE.CITY_COMBAT_BONUS;

        // FRIENDLY SUPPORT
        int attSupport = checkFriendlySupport(attPos);
        int defSupport = checkFriendlySupport(defPos);

        // COMBINED COMBAT STATS
        int combinedAtt = (attacker.getAttackingStrength() + attSupport)*rand.getNext()*attBonus;
        int combinedDef = (defender.getDefensiveStrength() + defSupport)*rand.getNext()*defBonus;
        return combinedAtt > combinedDef;
    }

    /**
     * Returns the number of supporting units for a given position
     * @param pos The position in question
     * @return The number of supporting units
     */
    private int checkFriendlySupport(Position pos) {
        int n = 0; // the current bonus from supporting units
        Player owner = World.getUnitAt(pos).getOwner(); // the owner of the unit
        ArrayList<Position> posList = Utility.nearestTileList(pos);

        posList.remove(pos);
        for (Position supportPos : posList) { // iterating through each nearby tile
            Unit unit = World.getUnitAt(supportPos); // retrieving the unit
            if (unit != null && unit.getOwner().equals(owner)) // if a friendly unit exists on the tile
                n++; // increase the number of supporting units
        }
        return n;
    }
}
